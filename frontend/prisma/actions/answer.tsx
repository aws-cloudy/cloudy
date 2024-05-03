'use server'

import prisma from '../client'
import { z } from 'zod'
import { CreateAnswer, DeleteAnswer } from '../schemas'
import { IPrismaError, IcreateAnswer } from '../types'

export async function createAnswer(values: z.infer<typeof CreateAnswer>) {
  const response: IcreateAnswer = {}
  const validated = CreateAnswer.safeParse(values)

  if (!validated.success) {
    response.error = 'answer: invalid fields'
    return response
  }
  const { postId, memberId, desc, memberName } = validated.data

  const question = await prisma.question.findUnique({ where: { id: postId } })
  if (!question) {
    response.error = 'answer: question not found '
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
    response.error = 'answer: an error occured while creating....'
    return response
  }
}

export async function deleteAnswer(values: z.infer<typeof DeleteAnswer>) {
  const validated = DeleteAnswer.safeParse(values)
  const response: IPrismaError = {}

  if (!validated.success) {
    response.error = 'delete answer: invalid fields'
    return response
  }

  const { id } = validated.data

  try {
    await prisma.answer.delete({ where: { id } })
    return response
  } catch (e) {
    response.error = 'delete answer: an error occured while deleting...'
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
    response.error = 'check anser: an error occured while checking answer...'
    return response
  }
}
