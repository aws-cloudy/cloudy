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
    response.error = 'question: invalid fields'
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
    response.error = 'question:an error occured while creating....'
    return response
  }

  return response
}

export async function deleteQuestion(value: z.infer<typeof DeleteQuestion>) {
  const validated = DeleteQuestion.safeParse(value)
  const response: IPrismaError = {}

  if (!validated.success) {
    response.error = 'delete question: invalid fields'
    return response
  }
  const { id } = validated.data

  try {
    await prisma.question.delete({ where: { id } })
  } catch (e) {
    console.log(e)
    response.error = 'delete question: an error occured while deleting quetion...'
    return response
  }
  return response
}

export async function updateQuestion(values: z.infer<typeof UpdateQuestion>) {
  const validated = UpdateQuestion.safeParse(values)
  const response: IPrismaError = {}

  if (!validated.success) {
    response.error = '업데이트 필드 에러'
    return response
  }
  const { desc, id, title } = validated.data

  try {
    await prisma.question.update({
      where: { id },
      data: { title, desc },
    })

    return response
  } catch (e) {
    response.error = '업데이트 중 에러 발생'
    return response
  }
}
