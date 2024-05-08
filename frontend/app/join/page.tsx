'use client'
import RegistInfomation from '@/components/RegistInfomation'
import styles from './page.module.scss'
import { useSearchParams } from 'next/navigation'
import { useEffect } from 'react'

const joinPage = () => {
  const searchParams = useSearchParams()
  const username = searchParams.get('username')

  return (
    <>
      <section className={styles.section}>
        <RegistInfomation username={username} />
      </section>
    </>
  )
}

export default joinPage
