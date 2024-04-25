import prisma from '@/prisma/client'
import { NextRequest, NextResponse } from 'next/server'

export async function GET(req: NextRequest) {
  console.log(req.nextUrl.searchParams.get('tag'))
  let tags: string[] = []
  if (req.nextUrl.searchParams.get('tag')) {
    tags = req.nextUrl.searchParams.get('tag')?.split('#') as string[]
  }
  const searchword = req.nextUrl.searchParams.get('searchword')

  const questionList = await prisma.question.findMany({
    orderBy: [{ id: 'desc' }],
    where: {
      AND: [
        tags.length > 0
          ? {
              hashtags: {
                some: {
                  hashtag: {
                    title: { in: tags },
                  },
                },
              },
            }
          : {},
        searchword ? { title: { contains: searchword } } : {},
      ],
    },
    include: {
      hashtags: {
        select: {
          hashtag: true,
        },
      },
    },
  })

  return NextResponse.json({ questionList })
}
