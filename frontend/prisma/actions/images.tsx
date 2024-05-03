import { z } from 'zod'
import { DeleteQuestionImage } from '../schemas'
import { IPrismaError } from '../types'
import prisma from '../client'
import { supabase } from '@/apis/supabase'

export async function deleteImage(values: z.infer<typeof DeleteQuestionImage>) {
  const validated = DeleteQuestionImage.safeParse(values)
  const response: IPrismaError = {}

  if (!validated.success) {
    response.error = { status: 400, code: 'CE001', msg: 'API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ' }
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
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }
}
