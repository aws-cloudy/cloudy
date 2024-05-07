import prisma from '../client'
import { IfetchHashtags } from '../types'

export async function fetchHashtags() {
  const response: IfetchHashtags = { hashtags: [] }

  try {
    const data = await prisma.hashtag.findMany({
      orderBy: [{ title: 'asc' }],
    })
    response.hashtags = data
    return response
  } catch (e) {
    response.error = { status: 500, code: 'SE001', msg: 'Internal Server Error / 데이터베이스 오류입니다.' }
    return response
  }
}
