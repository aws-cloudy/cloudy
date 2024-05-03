import { fetchQuestions } from '@/prisma/data/question'
import { NextRequest, NextResponse } from 'next/server'

export async function GET(req: NextRequest) {
  let tags: string[] = []

  if (req.nextUrl.searchParams.get('tag')) {
    tags = req.nextUrl.searchParams.get('tag')?.split('#') as string[]
  }
  const searchword = req.nextUrl.searchParams.get('searchword')
  const lastId = req.nextUrl.searchParams.get('lastId')

  const questionQuery = await fetchQuestions(tags, searchword, Number(lastId))

  if (questionQuery.error) {
    return NextResponse.json(
      {
        error: {
          status: 404,
          code: 'CE006',
          msg: '요청한 데이터가 존재하지 않습니다.',
        },
      },
      { status: 404 },
    )
  }

  const isLast = questionQuery.questions.length === 0

  return NextResponse.json(
    { questionList: questionQuery.questions, count: questionQuery.count, isLast },
    { status: 200 },
  )
}
