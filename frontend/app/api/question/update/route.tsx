import { CreateQH } from '@/prisma/schemas'
import { IHashtag, IUpdateQuestionAPI } from '@/types/community'
import { NextRequest, NextResponse } from 'next/server'
import { z } from 'zod'
import { createHashtag, deleteHashtag, updateQH } from '@/prisma/actions/hashtag'
import { updateQuestion } from '@/prisma/actions/question'
import { createQuestionImage, deleteQuestionImage } from '@/prisma/actions/questionImage'
import prisma from '@/prisma/client'
import { getUser } from '@/utils/getUser'

export async function PUT(req: NextRequest) {
  const { questionId, tags, title, desc, imageData }: IUpdateQuestionAPI = await req.json()

  const userQuery = await getUser()

  if (userQuery === undefined) {
    return NextResponse.json(
      { error: { status: 401, code: 'CA004', msg: '로그인 한 회원만 요청 가능합니다.' } },
      { status: 401 },
    )
  }

  const memberId = userQuery.id

  const question = await prisma.question.findUnique({ where: { id: Number(questionId) } })

  if (!question || memberId !== question.memberId) {
    return NextResponse.json(
      {
        error: {
          status: 403,
          code: 'CE005',
          msg: '작성자만 수정 가능합니다.',
        },
      },
      { status: 403 },
    )
  }

  const tagQuery = await createHashtag(tags)
  if (tagQuery.error) {
    return NextResponse.json({ error: tagQuery.error }, { status: tagQuery.error.status })
  }
  const { hashtags } = tagQuery

  const questionQuery = await updateQuestion({ id: Number(questionId), title, desc })
  if (questionQuery.error) {
    return NextResponse.json({ error: questionQuery.error }, { status: questionQuery.error.status })
  }

  await deleteQuestionImage(Number(questionId), desc)

  if (imageData.length > 0) {
    const imageQuery = await createQuestionImage({ items: imageData, questionId: Number(questionId) })
    if (imageQuery.error) {
      return NextResponse.json({ message: imageQuery.error }, { status: imageQuery.error.status })
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
      return NextResponse.json({ message: res.error }, { status: res.error.status })
    }

    await deleteHashtag()
  }

  return NextResponse.json({ question: questionQuery.question, hashtags }, { status: 201 })
}
