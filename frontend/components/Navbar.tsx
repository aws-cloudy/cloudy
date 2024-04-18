'use client'
import Button from '@/app/storybook/Button'
import { signIn, signOut, useSession } from 'next-auth/react'
import Link from 'next/link'
import { usePathname } from 'next/navigation'
import { useEffect } from 'react'

export default function Navbar() {
  const pathname = usePathname()
  // 세션 정보를 가져온다.
  const { data: session } = useSession()

  useEffect(() => {
    console.log('유저 정보', session)
  })

  return (
    <nav>
      <Link href="/">
        <h1>로그인테스트 !!!</h1>
      </Link>

      {session ? (
        // 세션 정보가 있다면 signOut() 호출
        <button onClick={() => signOut()}>Sign out</button>
      ) : (
        // 세션 정보가 없다면 signIn() 호출
        <button onClick={() => signIn()}>Sign in</button>
      )}
    </nav>
  )
}
