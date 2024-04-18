'use client'
import { signIn, useSession } from 'next-auth/react'
import { useRouter } from 'next/router'

export default function LoginPage() {
  //   const { data: session } = useSession()
  //   const router = useRouter()

  return (
    <div>
      <button onClick={() => signIn('google', { callbackUrl: '/' })} />
    </div>
  )
}
