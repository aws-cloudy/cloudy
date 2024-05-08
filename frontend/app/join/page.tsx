'use client'
import RegistInfomation from '@/components/RegistInfomation'
import styles from './page.module.scss'
import { useRouter, useSearchParams } from 'next/navigation'
import { useSession } from 'next-auth/react'
import { useEffect } from 'react'

const joinPage = () => {
  const { data: session, status } = useSession()
  const router = useRouter()
  const searchParams = useSearchParams()
  const username = searchParams.get('auth')

  useEffect(() => {
    if (status === 'authenticated') {
      alert('비정상적인 접근입니다.')
      router.push('/')
    }
  }, [status, router])

  return (
    <>
      <section className={styles.section}>
        <RegistInfomation username={username} />
      </section>
    </>
  )
}

export default joinPage
