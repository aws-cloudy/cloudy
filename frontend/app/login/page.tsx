'use client'
import LoginButton from '@/components/LoginButton'
import { useSession } from 'next-auth/react'
import { useRouter } from 'next/router'

export default function LoginPage() {
  const { data: session } = useSession()
  //   const router = useRouter()

  return (
    <div>
      <LoginButton session={session} />
    </div>
  )
}
