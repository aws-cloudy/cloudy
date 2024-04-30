import { CreateQH } from '@/prisma/schemas'
import { ICreateQuestion, IHashtag } from '@/types/community'
import { NextRequest, NextResponse } from 'next/server'
import { z } from 'zod'
import { auth } from '@/prisma/actions/auth'
import { createHashtag, createQH } from '@/prisma/actions/hashtag'
import { createQuestion } from '@/prisma/actions/question'
import { redirect } from 'next/navigation'

export async function POST(req: NextRequest) {
  const { tags, title, desc }: ICreateQuestion = await req.json()

  const userQuery = await auth()

  if (userQuery === undefined) {
    return NextResponse.json({ message: '로그인해주세요' }, { status: 401 })
  }

  const { memberId, memberName } = userQuery

  const tagQuery = await createHashtag(tags)
  if (tagQuery.error) {
    return NextResponse.json({ message: tagQuery.error }, { status: 400 })
  }
  const { hashtags } = tagQuery

  const questionQuery = await createQuestion({ memberId, memberName, title, desc })
  if (questionQuery.error) {
    return NextResponse.json({ message: questionQuery.error }, { status: 400 })
  }
  const { question } = questionQuery

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
      return NextResponse.json({ message: res.error }, { status: 400 })
    }
  }

  return NextResponse.json({ question }, { status: 201 })
}
