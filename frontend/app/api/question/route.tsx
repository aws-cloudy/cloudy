import prisma from '@/prisma/client'
import { fetchQuestions } from '@/prisma/data/question'
import { NextRequest, NextResponse } from 'next/server'

export async function GET(req: NextRequest) {
  console.log(req.nextUrl.searchParams.get('tag'))
  let tags: string[] = []

  if (req.nextUrl.searchParams.get('tag')) {
    tags = req.nextUrl.searchParams.get('tag')?.split('#') as string[]
  }
  const searchword = req.nextUrl.searchParams.get('searchword')
  const lastId = req.nextUrl.searchParams.get('lastId')

  const questionQuery = await fetchQuestions(tags, searchword, Number(lastId))

  if (questionQuery.error) {
    return NextResponse.json(null, { status: 400 })
  }

  const isLast = questionQuery.questions.length === 0

  return NextResponse.json({ questionList: questionQuery.questions, isLast }, { status: 200 })
}
