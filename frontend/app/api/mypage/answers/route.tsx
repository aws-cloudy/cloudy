import { fetchMyAnswers } from '@/prisma/data/mypage'
import { getUser } from '@/utils/getUser'
import { NextRequest, NextResponse } from 'next/server'

export async function GET(req: NextRequest) {
  const user = await getUser()

  if (!user?.id) {
    return NextResponse.json(
      { error: { status: 401, code: 'CA004', msg: '로그인 한 회원만 요청 가능합니다.' } },
      { status: 401 },
    )
  }

  const answerQuery = await fetchMyAnswers(user.id)

  if (answerQuery.error) {
    return NextResponse.json({ error: answerQuery.error }, { status: answerQuery.error.status })
  } else {
    return NextResponse.json({ answersList: answerQuery.answers, count: answerQuery.count }, { status: 200 })
  }
}
