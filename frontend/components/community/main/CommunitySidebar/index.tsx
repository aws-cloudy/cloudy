'use client'

import CommunitySidebarSearch from '../CommunitySidebarSearch'
import CommunitySidebarKeyword from '../CommunitySidebarKeyword'
import Button from '@/components/common/Button'
import { useRouter } from 'next/navigation'
import styles from './CommunitySidebar.module.scss'

function CommunitySidebar() {
  const route = useRouter()

  return (
    <div className={styles.container}>
      <CommunitySidebarSearch />
      <CommunitySidebarKeyword />
      <Button width="100%" onClick={() => route.push('/community/create')}>
        새 글 쓰기
      </Button>
    </div>
  )
}

export default CommunitySidebar
