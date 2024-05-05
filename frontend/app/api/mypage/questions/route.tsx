import { fetchMyQuestions } from '@/prisma/data/mypage'
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

  const questionQuery = await fetchMyQuestions(user.id)

  if (questionQuery.error) {
    return NextResponse.json({ error: questionQuery.error }, { status: questionQuery.error.status })
  } else {
    return NextResponse.json({ questionList: questionQuery.questions, count: questionQuery.count }, { status: 200 })
  }
}
