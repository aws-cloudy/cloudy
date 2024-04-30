import { createAnswer } from '@/prisma/actions/answer'
import { auth } from '@/prisma/actions/auth'
import { NextRequest, NextResponse } from 'next/server'

export async function POST(req: NextRequest) {
  const { postId, desc }: { postId: string; desc: string } = await req.json()

  const userQuery = await auth()

  if (userQuery === undefined) {
    return NextResponse.json({ message: '로그인해주세요' }, { status: 401 })
  }

  const { memberId, memberName } = userQuery

  const id = Number(postId)
  if (id) {
    const answerQuery = await createAnswer({ postId: id, desc, memberId, memberName })

    if (answerQuery.error) {
      return NextResponse.json({ message: answerQuery.error }, { status: 400 })
    }
    return NextResponse.json({ ...answerQuery.answer })
  }
}
