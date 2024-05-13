'use client'

import { useTokenActions } from '@/stores/authStore'
import { SessionProvider, getSession } from 'next-auth/react'
import { usePathname } from 'next/navigation'
import { useEffect, useState } from 'react'

type Props = {
  children: React.ReactNode
}

export default function Auth({ children }: Props) {
  const { resetUser, setUser } = useTokenActions()
  const [isChecking, setIsCheking] = useState(true)
  const pathname = usePathname()

  const checkToken = async () => {
    const session = await getSession()
    if (!session?.accessToken) {
      resetUser()
    } else {
      setUser()
    }
    setIsCheking(false)
  }

  useEffect(() => {
    checkToken()
  }, [pathname])

  return <SessionProvider>{!isChecking && children}</SessionProvider>
}
