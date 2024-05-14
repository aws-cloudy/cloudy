'use client'

import RegistInfomation from '@/components/RegistInfomation'
import styles from './page.module.scss'
import { useRouter } from 'next/navigation'
import { useSession } from 'next-auth/react'
import { Suspense, useEffect } from 'react'

const joinPage = ({ searchParams }: { searchParams: { auth: string } }) => {
  const { data: session, status } = useSession()
  const router = useRouter()

  const username = decodeURIComponent(searchParams.auth)

  useEffect(() => {
    if (status === 'authenticated') {
      alert('비정상적인 접근입니다.')
      router.push('/')
    }
  }, [status, router])

  return (
    <>
      <section className={styles.section}>
        <Suspense>
          <RegistInfomation username={username} />
        </Suspense>
      </section>
    </>
  )
}

export default joinPage
