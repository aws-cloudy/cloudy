import { createHashtag, createQH, createQuestion } from '@/prisma/actions'
import { CreateQH } from '@/prisma/schemas'
import { ICreateQuestion, IHashtag } from '@/types/community'
import { NextRequest, NextResponse } from 'next/server'
import { z } from 'zod'

const user = {
  memberId: 1,
  memberName: '김싸피',
}

export async function POST(req: NextRequest) {
  const { tags, title, desc }: ICreateQuestion = await req.json()

  const tagQuery = await createHashtag(tags)
  if (tagQuery.error) {
    return NextResponse.json({ message: tagQuery.error }, { status: 400 })
  }
  const { hashtags } = tagQuery

  const questionQuery = await createQuestion({ ...user, title, desc })
  if (questionQuery.error) {
    return NextResponse.json({ message: questionQuery.error }, { status: 400 })
  }
  const { question } = questionQuery

  if (hashtags && question) {
    const QH: z.infer<typeof CreateQH>[] = hashtags.reduce(
      (arr: { questionId: number; hashtagId: number }[], hashtag: IHashtag) => {
        if (hashtag.id) {
          return [...arr, { questionId: question.id, hashtagId: hashtag.id }]
        } else {
          return arr
        }
      },
      [],
    )

    const res = await createQH(QH)
    if (res.error) {
      return NextResponse.json({ message: res.error }, { status: 400 })
    }
  }

  return NextResponse.json({ ...question }, { status: 201 })
}
