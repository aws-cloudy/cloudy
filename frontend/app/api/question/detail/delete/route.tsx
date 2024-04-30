import { deleteHashtag } from '@/prisma/actions/hashtag'
import { deleteQuestion } from '@/prisma/actions/question'
import { NextRequest, NextResponse } from 'next/server'

export async function DELETE(req: NextRequest) {
  const id = req.nextUrl.searchParams.get('id')

  if (!id) {
    return NextResponse.json({ mesasge: '아이디 누락' }, { status: 400 })
  }

  const deleteQuery = await deleteQuestion({ id: Number(id) })
  await deleteHashtag()

  if (deleteQuery.error) {
    return NextResponse.json({ message: deleteQuery.error }, { status: 400 })
  }

  return NextResponse.json(null, { status: 200 })
}
