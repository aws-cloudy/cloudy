import prisma from '../client'
import { IfetchQuestions } from '../types'
import { unstable_noStore as noStore } from 'next/cache'

export async function fetchQuestions(tags: string[], searchword: string | null, lastId: number) {
  const response: IfetchQuestions = { questions: [], count: 0 }

  const isFirstPage = !Boolean(lastId)

  const where = {
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
  }

  const pageCondition = {
    skip: 1,
    cursor: {
      id: lastId,
    },
  }

  const count = await prisma.question.count({ where })

  if (count) {
    response.count = count
  }

  try {
    const questionList = await prisma.question.findMany({
      orderBy: [{ id: 'desc' }],
      where,
      include: {
        _count: {
          select: { answers: true },
        },
        hashtags: {
          select: {
            hashtag: true,
          },
        },
      },
      take: 5,
      ...(!isFirstPage && pageCondition),
    })

    if (questionList) {
      response.questions = questionList
    }
    return response
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    console.log(e)
    return response
  }
}

export async function fetchQuestionDetail(id: number) {
  noStore()

  try {
    const data = await prisma.question.findUnique({
      where: { id },
      include: {
        answers: true,
        hashtags: {
          select: {
            hashtag: true,
          },
        },
      },
    })
    if (data) {
      return data
    }
  } catch (e) {
    console.log('An error occured during fetching question detail...')
  }
}
