import { fetchQuestionDetail } from '@/prisma/data'
import { NextRequest, NextResponse } from 'next/server'

export async function GET(req: NextRequest) {
  const id = req.nextUrl.searchParams.get('id')
  const numId = Number(id)

  if (numId) {
    const data = await fetchQuestionDetail(Number(id))

    if (data) {
      return NextResponse.json({ ...data }, { status: 200 })
    } else {
      return NextResponse.json({ id: null }, { status: 200 })
    }
  }
}
