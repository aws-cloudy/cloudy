'use server'

import prisma from '../client'
import { z } from 'zod'
import { CreateAnswer, DeleteAnswer } from '../schemas'
import { IPrismaError, IcreateAnswer } from '../types'

export async function createAnswer(values: z.infer<typeof CreateAnswer>) {
  const response: IcreateAnswer = {}
  const validated = CreateAnswer.safeParse(values)

  if (!validated.success) {
    response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
    return response
  }
  const { postId, memberId, desc, memberName } = validated.data

  const question = await prisma.question.findUnique({ where: { id: postId } })
  if (!question) {
    response.error = { status: 404, code: 'CE006', msg: '요청한 데이터가 존재하지 않습니다.' }
    return response
  }

  try {
    const answer = await prisma.answer.create({
      data: {
        memberId,
        memberName,
        desc,
        questionId: question.id,
      },
    })

    response.answer = answer
    return response
  } catch (e) {
    console.log(e)
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }
}

export async function deleteAnswer(values: z.infer<typeof DeleteAnswer>) {
  const validated = DeleteAnswer.safeParse(values)
  const response: IPrismaError = {}

  if (!validated.success) {
    response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
    return response
  }

  const { id } = validated.data

  try {
    await prisma.answer.delete({ where: { id } })
    return response
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }
}

export async function checkAnswer(postId: number, ansId: number | null) {
  const response: IPrismaError = {}

  try {
    await prisma.question.update({
      where: { id: postId },
      data: {
        checkedId: ansId,
      },
    })
    return response
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }
}
