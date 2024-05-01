import { z } from 'zod'
import { CreateQuestionImage } from '../schemas'
import { IPrismaError } from '../types'
import prisma from '../client'

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
