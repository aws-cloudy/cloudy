'use client'
import { signIn, signOut, useSession } from 'next-auth/react'
import Link from 'next/link'
import { usePathname } from 'next/navigation'
import { useEffect } from 'react'
import classes from './index.module.scss'
import google from '@/public/img/google.png'
import cognito from '@/public/img/cognito.png'
import Image from 'next/image'

export default function LoginButton({ session }: any) {
  const pathname = usePathname()
  // 세션 정보를 가져온다.
  // const { data: session } = useSession()

  useEffect(() => {
    console.log('유저 정보', session)
  })

  const handleSignInGoogle = () => {
    // 구글 로그인 제공자를 명시적으로 지정
    // 로그인 요청 후 리디렉션 페이지는 메인 페이지
    signIn()
  }

  const handleSignInCognito = () => {
    signIn()
  }

  return (
    <nav>
      <Link href="/">
        <h1>로그인테스트 !!!</h1>
      </Link>

      {session && session.user ? (
        // 세션 정보가 있다면 signOut() 호출
        <>
          <p>{session.user?.name}님 안녕하세요!</p>
          <button onClick={() => signOut()}>로그아웃</button>
          {/* <button onClick={() => signOut({ callbackUrl: '/api/auth/logout' })}>코그니토로그아웃</button> */}
        </>
      ) : (
        // 세션 정보가 없다면 signIn() 호출
        <>
          <button onClick={handleSignInGoogle} className={classes.googleButton}>
            <Image src={google} alt="Google" className={classes.icon} />
            Sign in with Google
          </button>
          <button onClick={handleSignInCognito} className={classes.cognitoButton}>
            <Image src={cognito} alt="Cognito" className={classes.icon} />
            Sign in with Cognito
          </button>
        </>
      )}
    </nav>
  )
}
