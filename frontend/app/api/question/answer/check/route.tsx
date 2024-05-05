import { checkAnswer } from '@/prisma/actions/answer'
import { getUser } from '@/utils/getUser'
import { NextRequest, NextResponse } from 'next/server'
import prisma from '@/prisma/client'

export async function PUT(req: NextRequest) {
  const { postId, ansId } = await req.json()

  if (!postId) {
    return NextResponse.json(
      { error: { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' } },
      { status: 400 },
    )
  }

  const user = await getUser()
  const post = await prisma.question.findUnique({ where: { id: Number(postId) } })

  if (!post) {
    return NextResponse.json(
      { error: { status: 404, code: 'CE006', msg: '요청한 데이터가 존재하지 않습니다.' } },
      { status: 404 },
    )
  } else if (post.memberId !== user?.id) {
    return NextResponse.json(
      { error: { status: 403, code: 'CE005', msg: '작성자만 삭제 가능합니다. ' } },
      { status: 403 },
    )
  }

  if (ansId) {
    const answer = await prisma.answer.findUnique({ where: { id: Number(ansId) } })

    if (!answer) {
      return NextResponse.json(
        { error: { status: 404, code: 'CE006', msg: '요청한 데이터가 존재하지 않습니다.' } },
        { status: 404 },
      )
    }
  }

  try {
    await checkAnswer(postId, ansId)
    return NextResponse.json(null, { status: 200 })
  } catch (e) {
    return NextResponse.json(
      { error: { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' } },
      { status: 500 },
    )
  }
}
