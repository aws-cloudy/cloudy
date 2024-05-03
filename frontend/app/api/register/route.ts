// app/api/regist/route.ts
import { saveUserDetails } from '@/utils/cognito'
import type { NextApiRequest, NextApiResponse } from 'next'
import { getToken } from 'next-auth/jwt'

// 이 부분에 데이터베이스 업데이트 로직을 구현할 수 있는 함수를 가져옵니다.
// 예: import { updateUserInDatabase } from '@/services/userService';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  if (req.method !== 'POST') {
    return res.status(405).end('Method Not Allowed')
  }

  const token = await getToken({ req })
  if (!token) {
    return res.status(401).json({ error: 'Unauthorized' })
  }

  const { jobId, serviceId } = req.body

  try {
    // 데이터베이스에 사용자 정보 업데이트
    await saveUserDetails(token.sub, jobId, serviceId)

    // 성공 응답
    return res.status(200).json({ message: 'User information updated successfully' })
  } catch (error) {
    console.error(error)
    return res.status(500).json({ error: 'Internal Server Error' })
  }
}
