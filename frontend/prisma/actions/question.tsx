'use server'

import prisma from '../client'
import { CreateQuestion, DeleteQuestion, UpdateQuestion } from '../schemas'
import { z } from 'zod'
import { IPrismaError, IcreateQuestion } from '../types'

export async function createQuestion(values: z.infer<typeof CreateQuestion>) {
  const response: IcreateQuestion = {}

  const validated = CreateQuestion.safeParse(values)
  if (!validated.success) {
    console.log(validated.error.flatten().fieldErrors)
    response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
    return response
  }

  const { memberId, memberName, title, desc } = validated.data

  try {
    const question = await prisma.question.create({
      data: {
        memberId,
        memberName,
        title,
        desc,
      },
    })

    if (question) {
      response.question = question
    }
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }

  return response
}

export async function deleteQuestion(value: z.infer<typeof DeleteQuestion>) {
  const validated = DeleteQuestion.safeParse(value)
  const response: IPrismaError = {}

  if (!validated.success) {
    response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
    return response
  }
  const { id } = validated.data

  try {
    await prisma.question.delete({ where: { id } })
  } catch (e) {
    console.log(e)
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }
  return response
}

export async function updateQuestion(values: z.infer<typeof UpdateQuestion>) {
  const validated = UpdateQuestion.safeParse(values)
  const response: IcreateQuestion = {}

  if (!validated.success) {
    response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
    return response
  }
  const { desc, id, title } = validated.data

  try {
    const res = await prisma.question.update({
      where: { id },
      data: { title, desc },
    })
    response.question = res
    return response
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }
}
