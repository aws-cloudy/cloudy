import prisma from '@/prisma/client'
import { auth } from '@/prisma/actions/auth'
import { NextRequest, NextResponse } from 'next/server'
import { checkAnswer, deleteAnswer } from '@/prisma/actions/answer'

export async function DELETE(req: NextRequest) {
  const idParam = req.nextUrl.searchParams.get('id')
  if (!idParam) return

  const id = parseInt(idParam)
  const userQuery = await auth()

  if (userQuery === undefined) {
    return NextResponse.json(
      { error: { status: 401, code: 'CA004', msg: '로그인 한 회원만 요청 가능합니다.' } },
      { status: 401 },
    )
  }

  const { memberId } = userQuery

  const answer = await prisma.answer.findUnique({ where: { id } })

  if (!answer) {
    return NextResponse.json(
      { error: { status: 404, code: 'CE006', msg: '요청한 데이터가 존재하지 않습니다.' } },
      { status: 404 },
    )
  } else if (answer.memberId !== memberId) {
    return NextResponse.json(
      { error: { status: 403, code: 'CE005', msg: '작성자만 삭제 가능합니다. ' } },
      { status: 403 },
    )
  }

  try {
    const question = await prisma.question.findUnique({ where: { id: Number(answer.questionId) } })

    if (!question) {
      return NextResponse.json(
        { error: { status: 404, code: 'CE006', msg: '요청한 데이터가 존재하지 않습니다.' } },
        { status: 404 },
      )
    }

    if (answer.id === question.checkedId) {
      await checkAnswer(question.id, null)
    }
  } catch (e) {
    return NextResponse.json(
      { error: { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' } },
      { status: 500 },
    )
  }

  const deleteQuery = await deleteAnswer({ id })

  if (deleteQuery?.error) {
    return NextResponse.json({ message: deleteQuery.error }, { status: deleteQuery.error.status })
  }
  return NextResponse.json(null, { status: 200 })
}
