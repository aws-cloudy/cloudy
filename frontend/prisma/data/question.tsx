import { IQuestion } from '@/types/community'
import prisma from '../client'

export async function fetchQuestions(tags: string[], searchword: string | null, lastId: number) {
  const response: { error?: string; questions: IQuestion[] } = { questions: [] }

  const isFirstPage = !Boolean(lastId)

  const pageCondition = {
    skip: 1,
    cursor: {
      id: lastId,
    },
  }

  try {
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

    console.log(questionList)
    if (questionList) {
      response.questions = questionList
    }
    return response
  } catch (e) {
    response.error = 'fetch error'
    console.log(e)
    return response
  }
}

export async function fetchQuestionDetail(id: number) {
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
