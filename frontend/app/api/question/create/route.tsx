import { CreateQH } from '@/prisma/schemas'
import { ICreateQuestion, IHashtag } from '@/types/community'
import { NextRequest, NextResponse } from 'next/server'
import { z } from 'zod'
import { auth } from '@/prisma/actions/auth'
import { createHashtag, createQH } from '@/prisma/actions/hashtag'
import { createQuestion } from '@/prisma/actions/question'
import { createQuestionImage } from '@/prisma/actions/questionImage'

export async function POST(req: NextRequest) {
  const { tags, title, desc, imageData }: ICreateQuestion = await req.json()

  const userQuery = await auth()

  if (userQuery === undefined) {
    return NextResponse.json(
      { error: { status: 401, code: 'CA004', msg: '로그인 한 회원만 요청 가능합니다.' } },
      { status: 401 },
    )
  }

  const { memberId, memberName } = userQuery

  const tagQuery = await createHashtag(tags)
  if (tagQuery.error) {
    return NextResponse.json({ error: tagQuery.error }, { status: tagQuery.error.status })
  }
  const { hashtags } = tagQuery

  const questionQuery = await createQuestion({ memberId, memberName, title, desc })
  if (questionQuery.error) {
    return NextResponse.json({ error: questionQuery.error }, { status: questionQuery.error.status })
  }
  const { question } = questionQuery

  if (question && imageData.length > 0) {
    const imageQuery = await createQuestionImage({ items: imageData, questionId: question.id })
    if (imageQuery.error) {
      return NextResponse.json({ error: imageQuery.error }, { status: imageQuery.error.status })
    }
  }

  if (hashtags && question) {
    const QH: z.infer<typeof CreateQH>[] = hashtags.reduce(
      (arr: { questionId: number; hashtagId: number }[], hashtag: IHashtag) => {
        if (hashtag.id) {
          return [...arr, { questionId: Number(question.id), hashtagId: hashtag.id }]
        } else {
          return arr
        }
      },
      [],
    )

    const res = await createQH(QH)
    if (res.error) {
      return NextResponse.json({ error: res.error }, { status: res.error.status })
    }
  }

  return NextResponse.json({ question, hashtags }, { status: 201 })
}
