import { ISessionUser } from '@/types/user'
import { getServerSession } from 'next-auth'
import { authOptions } from './authOptions'

export async function getUser() {
  const session = await getServerSession(authOptions)

  if (!session?.user) {
    return undefined
  } else {
    return session.user
  }
}
