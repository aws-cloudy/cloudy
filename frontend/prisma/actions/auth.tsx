'use server'

import { ISessionUser } from '@/types/user'
import { authOptions } from '@/utils/authOptions'
import { getServerSession } from 'next-auth'

export async function auth() {
  const session = await getServerSession(authOptions)
  const user = session?.user as ISessionUser

  if (!user) {
    return undefined
  } else {
    return { memberId: user.id, memberName: user.name }
  }
}
