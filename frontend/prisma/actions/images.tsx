import { z } from 'zod'
import { DeleteQuestionImage } from '../schemas'
import { IPrismaError } from '../types'
import prisma from '../client'
import { supabase } from '@/apis/supabase'

export async function deleteImage(values: z.infer<typeof DeleteQuestionImage>) {
  const validated = DeleteQuestionImage.safeParse(values)
  const response: IPrismaError = {}

  if (!validated.success) {
    response.error = '이미지 삭제 필드 에러'
    return response
  }

  const { questionId } = validated.data

  try {
    const images = await prisma.questionimage.findMany({
      where: {
        questionId,
      },
    })

    const imagePath = images.map(each => each.path)
    await supabase.storage.from('cloudy_image').remove(imagePath)
    return response
  } catch (e) {
    response.error = '이미지 삭제 중 에러'
    return response
  }
}
