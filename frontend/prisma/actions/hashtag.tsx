'use server'

import prisma from '../client'
import { z } from 'zod'
import { CreateHashtag, CreateQH } from '../schemas'
import { Hashtag, Prisma } from '@prisma/client'
import { DefaultArgs } from '@prisma/client/runtime/library'
import { IPrismaError, IcreateHashtag } from '../types'

export async function createHashtag(values: z.infer<typeof CreateHashtag>[]) {
  const rawHash: Prisma.Prisma__HashtagClient<Hashtag, never, DefaultArgs>[] = []
  const hashtags: Hashtag[] = []
  let response: IcreateHashtag = {}

  const maketag = values.map(async value => {
    const validated = CreateHashtag.safeParse(value)
    if (!validated.success) {
      console.log(validated.error.flatten().fieldErrors)
      response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
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
        response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
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
        response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
        return
      }
    }
  })

  await Promise.all(maketag)

  const newhash = await prisma.$transaction([...rawHash])
  response.hashtags = hashtags.concat(newhash)

  return response
}

export async function createQH(values: z.infer<typeof CreateQH>[]) {
  let response: IPrismaError = {}
  let data: z.infer<typeof CreateQH>[] = []

  values.forEach(async value => {
    const validated = CreateQH.safeParse(value)
    if (!validated.success) {
      response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
      return
    }
    data.push(validated.data)
  })

  try {
    await prisma.questionhash.createMany({
      data,
    })
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }

  return response
}

export async function deleteHashtag() {
  return await prisma.hashtag.deleteMany({
    where: {
      questions: {
        none: {},
      },
    },
  })
}

export async function updateQH(values: z.infer<typeof CreateQH>[], questionId: number) {
  let response: IPrismaError = {}
  let data: z.infer<typeof CreateQH>[] = []

  values.forEach(async value => {
    const validated = CreateQH.safeParse(value)
    if (!validated.success) {
      response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
      return
    }
    data.push(validated.data)
  })

  try {
    await prisma.questionhash.deleteMany({
      where: {
        questionId,
      },
    })

    await prisma.questionhash.createMany({
      data,
    })
  } catch (e) {
    console.log(e)
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }

  return response
}
