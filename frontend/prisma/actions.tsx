'use server'

import prisma from './client'
import { z } from 'zod'
import { CreateAnswer, CreateHashtag, CreateQH, CreateQuestion } from './schemas'
import { Prisma } from '@prisma/client'
import { DefaultArgs } from '@prisma/client/runtime/library'
import { IAnswer, IHashtag, IQuestion } from '@/types/community'

export async function createHashtag(values: z.infer<typeof CreateHashtag>[]) {
  const rawHash: Prisma.Prisma__HashtagClient<
    {
      id: number
      title: string
    },
    never,
    DefaultArgs
  >[] = []
  const hashtags: IHashtag[] = []
  let response: { error?: string; hashtags?: IHashtag[] } = {}

  values.forEach(async value => {
    const validated = CreateHashtag.safeParse(value)
    if (!validated.success) {
      console.log(validated.error.flatten().fieldErrors)
      response.error = 'hashtag: invalid fields'
      return
    }
    const { id, title } = validated.data

    if (id) {
      try {
        const hash = await prisma.hashtag.findUnique({
          where: { id },
        })
        if (hash) {
          hashtags.push(hash)
        }
      } catch (e) {
        console.log('error: hashtag: id exists')
        response.error = 'hashtag: id exists'
        return
      }
    } else {
      try {
        const hash = prisma.hashtag.upsert({
          where: { title },
          update: {},
          create: { title },
        })
        rawHash.push(hash)
      } catch (e) {
        console.log('error: hashtag: create')
        response.error = 'hashtag: an error occured while creating....'
        return
      }
    }
  })

  const newhash = await prisma.$transaction([...rawHash])
  response.hashtags = hashtags.concat(newhash)

  return response
}

export async function createQuestion(values: z.infer<typeof CreateQuestion>) {
  const response: { error?: string; question?: IQuestion } = {}

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

export async function createQH(values: z.infer<typeof CreateQH>[]) {
  let response: { error?: string } = {}
  let data: z.infer<typeof CreateQH>[] = []

  values.forEach(async value => {
    const validated = CreateQH.safeParse(value)
    if (!validated.success) {
      response.error = 'QH: field error'
      return
    }
    data.push(validated.data)
  })

  try {
    await prisma.questionhash.createMany({
      data,
    })
  } catch (e) {
    response.error = 'QH: server error'
    return response
  }

  return response
}

export async function createAnswer(values: z.infer<typeof CreateAnswer>) {
  const response: { error?: string; answer?: IAnswer } = {}
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
