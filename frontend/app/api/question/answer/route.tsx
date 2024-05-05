import { createAnswer } from '@/prisma/actions/answer'
import { auth } from '@/prisma/actions/auth'
import { NextRequest, NextResponse } from 'next/server'

export async function POST(req: NextRequest) {
  const { postId, desc }: { postId: string; desc: string } = await req.json()

  const userQuery = await auth()

  if (userQuery === undefined) {
    return NextResponse.json(
      { error: { status: 401, code: 'CA004', msg: '로그인 한 회원만 요청 가능합니다.' } },
      { status: 401 },
    )
  }

  const { memberId, memberName } = userQuery

  const id = Number(postId)
  if (id) {
    const answerQuery = await createAnswer({ postId: id, desc, memberId, memberName })

    if (answerQuery.error) {
      return NextResponse.json({ error: answerQuery.error }, { status: answerQuery.error.status })
    }
    return NextResponse.json({ ...answerQuery.answer })
  }
}
