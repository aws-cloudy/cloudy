import { z } from 'zod'
import { CreateQuestionImage, DeleteQuestionImage } from '../schemas'
import { IPrismaError } from '../types'
import prisma from '../client'
import { supabase } from '@/apis/supabase'

export async function createQuestionImage(values: {
  items: z.infer<typeof CreateQuestionImage>[]
  questionId: number
}) {
  const response: IPrismaError = {}
  const { items, questionId } = values

  items.forEach(async item => {
    const validated = CreateQuestionImage.safeParse(item)
    if (!validated.success) {
      response.error = '이미지 생성 필드 오류'
      return
    }

    const { path, url } = validated.data

    try {
      await prisma.questionimage.createMany({
        data: {
          path,
          url,
          questionId,
        },
      })
      return
    } catch (e) {
      response.error = '이미지 필드 생성 중 에러 발생'
      return
    }
  })

  return response
}

export async function deleteQuestionImage(questionId: number, desc: string) {
  const response: IPrismaError = {}

  const questions = await prisma.questionimage.findMany({
    where: {
      questionId,
    },
  })

  if (!questions) return
  const notExist = questions.filter(each => !desc.match(each.url)).map(each => each.path)

  try {
    await prisma.questionimage.deleteMany({
      where: {
        path: {
          in: notExist,
        },
      },
    })

    await supabase.storage.from('cloudy_image').remove(notExist)
  } catch (e) {
    response.error = '이미지 삭제 중 에러 발생'
  }
  return response
}
