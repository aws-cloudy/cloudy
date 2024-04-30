import { checkAnswer } from '@/prisma/actions/answer'
import { NextRequest, NextResponse } from 'next/server'

export async function PUT(req: NextRequest) {
  const { postId, ansId } = await req.json()

  try {
    const checkQuery = checkAnswer(postId, ansId)
    return NextResponse.json(null, { status: 200 })
  } catch (e) {
    return NextResponse.json({ message: '채택 실패' }, { status: 400 })
  }
}
