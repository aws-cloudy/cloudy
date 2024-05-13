import CommunitySidebarSearch from '../CommunitySidebarSearch'
import CommunitySidebarKeyword from '../CommunitySidebarKeyword'
import Button from '@/components/common/Button'
import { useRouter } from 'next/navigation'
import styles from './CommunitySidebar.module.scss'
import { useIsLogin } from '@/stores/authStore'

function CommunitySidebar() {
  const route = useRouter()
  const isLogin = useIsLogin()

  return (
    <div className={styles.container}>
      <CommunitySidebarSearch />
      <CommunitySidebarKeyword />
      {isLogin && (
        <Button width="100%" onClick={() => route.push('/community/create')}>
          새 글 쓰기
        </Button>
      )}
    </div>
  )
}

export default CommunitySidebar
