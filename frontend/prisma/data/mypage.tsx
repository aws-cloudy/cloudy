import prisma from '../client'
import { IFetchMyAnswers, IfetchQuestions } from '../types'

export async function fetchMyQuestions(memberId: string) {
  const response: IfetchQuestions = { questions: [], count: 0 }

  try {
    const count = await prisma.question.count({ where: { memberId } })
    response.count = count

    const questionList = await prisma.question.findMany({
      where: {
        memberId,
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
    })

    response.questions = questionList
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
  }

  return response
}

export async function fetchMyAnswers(memberId: string) {
  const response: IFetchMyAnswers = { answers: [], count: 0 }

  try {
    const count = await prisma.answer.count({ where: { memberId } })
    response.count = count

    const answerList = await prisma.answer.findMany({
      where: {
        memberId,
      },
    })
    response.answers = answerList
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
  }

  return response
}
