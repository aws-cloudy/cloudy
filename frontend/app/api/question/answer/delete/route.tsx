import prisma from '@/prisma/client'
import { auth } from '@/prisma/actions/auth'
import { NextRequest, NextResponse } from 'next/server'
import { deleteAnswer } from '@/prisma/actions/answer'

export async function DELETE(req: NextRequest) {
  const idParam = req.nextUrl.searchParams.get('id')
  if (!idParam) return

  const id = parseInt(idParam)
  const userQuery = await auth()

  if (userQuery === undefined) {
    return NextResponse.json({ message: '로그인해주세요' }, { status: 401 })
  }

  const { memberId, memberName } = userQuery

  const answer = await prisma.answer.findUnique({ where: { id } })

  if (!answer) {
    return NextResponse.json({ message: '댓글을 찾을 수 없습니다.' }, { status: 404 })
  } else if (answer.memberId !== memberId) {
    return NextResponse.json({ message: '권한이 없습니다.' }, { status: 403 })
  }

  const deleteQuery = await deleteAnswer({ id })

  if (deleteQuery?.error) {
    return NextResponse.json({ message: deleteQuery.error }, { status: 400 })
  }
  return NextResponse.json(null, { status: 200 })
}
