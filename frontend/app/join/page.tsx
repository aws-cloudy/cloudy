'use client'
import RegistInfomation from '@/components/RegistInfomation'
import styles from './page.module.scss'
import { useSearchParams } from 'next/navigation'

const joinPage = () => {
  const searchParams = useSearchParams()
  const username = searchParams.get('auth')

  return (
    <>
      <section className={styles.section}>
        <RegistInfomation username={username} />
      </section>
    </>
  )
}

export default joinPage
