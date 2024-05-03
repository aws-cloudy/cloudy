import { CreateQH } from '@/prisma/schemas'
import { IHashtag, IUpdateQuestionAPI } from '@/types/community'
import { NextRequest, NextResponse } from 'next/server'
import { z } from 'zod'
import { auth } from '@/prisma/actions/auth'
import { createHashtag, deleteHashtag, updateQH } from '@/prisma/actions/hashtag'
import { updateQuestion } from '@/prisma/actions/question'
import { createQuestionImage, deleteQuestionImage } from '@/prisma/actions/questionImage'
import prisma from '@/prisma/client'

export async function PUT(req: NextRequest) {
  const { questionId, tags, title, desc, imageData }: IUpdateQuestionAPI = await req.json()

  const userQuery = await auth()

  if (userQuery === undefined) {
    return NextResponse.json({ message: '로그인해주세요' }, { status: 401 })
  }

  const { memberId } = userQuery

  const question = await prisma.question.findUnique({ where: { id: Number(questionId) } })

  if (!question || memberId !== question.memberId) {
    return NextResponse.json({ message: '권한이 없습니다.' }, { status: 403 })
  }

  const tagQuery = await createHashtag(tags)
  if (tagQuery.error) {
    return NextResponse.json({ message: tagQuery.error }, { status: 400 })
  }
  const { hashtags } = tagQuery

  const questionQuery = await updateQuestion({ id: Number(questionId), title, desc })
  if (questionQuery.error) {
    return NextResponse.json({ message: questionQuery.error }, { status: 400 })
  }

  await deleteQuestionImage(Number(questionId), desc)

  if (imageData.length > 0) {
    const imageQuery = await createQuestionImage({ items: imageData, questionId: Number(questionId) })
    if (imageQuery.error) {
      return NextResponse.json({ message: imageQuery.error }, { status: 400 })
    }
  }

  if (hashtags) {
    const QH: z.infer<typeof CreateQH>[] = hashtags.reduce(
      (arr: { questionId: number; hashtagId: number }[], hashtag: IHashtag) => {
        if (hashtag.id) {
          return [...arr, { questionId: Number(questionId), hashtagId: hashtag.id }]
        } else {
          return arr
        }
      },
      [],
    )

    const res = await updateQH(QH, Number(questionId))
    if (res.error) {
      return NextResponse.json({ message: res.error }, { status: 400 })
    }

    await deleteHashtag()
  }

  return NextResponse.json(null, { status: 201 })
}
