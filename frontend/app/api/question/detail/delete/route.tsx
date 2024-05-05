import { deleteHashtag } from '@/prisma/actions/hashtag'
import { deleteImage } from '@/prisma/actions/images'
import { deleteQuestion } from '@/prisma/actions/question'
import { NextRequest, NextResponse } from 'next/server'
import prisma from '@/prisma/client'
import { getUser } from '@/utils/getUser'

export async function DELETE(req: NextRequest) {
  const id = req.nextUrl.searchParams.get('id')

  if (!id) {
    return NextResponse.json(
      { error: { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다.' } },
      { status: 400 },
    )
  }

  const user = await getUser()

  if (!user?.id) {
    return NextResponse.json(
      { error: { status: 401, code: 'CA004', msg: '로그인 한 회원만 요청 가능합니다.' } },
      { status: 401 },
    )
  }
  const question = await prisma.question.findUnique({ where: { id: Number(id) } })

  if (!question) {
    return NextResponse.json(
      { error: { status: 404, code: 'CE006', msg: '요청한 데이터가 존재하지 않습니다.' } },
      { status: 404 },
    )
  }

  if (question.memberId !== user.id) {
    return NextResponse.json(
      { error: { status: 403, code: 'CE005', msg: '작성자만 삭제 가능합니다. ' } },
      { status: 403 },
    )
  }

  const deleteImageQuery = await deleteImage({ questionId: Number(id) })

  if (deleteImageQuery.error) {
    return NextResponse.json({ error: deleteImageQuery.error }, { status: deleteImageQuery.error.status })
  }

  const deleteQuery = await deleteQuestion({ id: Number(id) })
  await deleteHashtag()

  if (deleteQuery.error) {
    return NextResponse.json({ error: deleteQuery.error }, { status: deleteQuery.error.status })
  }

  return NextResponse.json(null, { status: 200 })
}
