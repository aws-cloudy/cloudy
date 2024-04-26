import { createAnswer } from '@/prisma/actions'
import { revalidatePath } from 'next/cache'
import { NextRequest, NextResponse } from 'next/server'

const user = {
  memberId: 1,
  memberName: '김싸피',
}

export async function POST(req: NextRequest) {
  const { postId, desc }: { postId: string; desc: string } = await req.json()
  const id = Number(postId)
  if (id) {
    const answerQuery = await createAnswer({ postId: id, desc, ...user })

    if (answerQuery.error) {
      return NextResponse.json({ message: answerQuery.error }, { status: 400 })
    }
    return NextResponse.json({ ...answerQuery.answer })
  }
}
