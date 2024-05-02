import { getUser } from '@/utils/getUser'
import styles from './Protected.module.scss'

async function Protected({ children }: { children: React.ReactNode }) {
  const user = await getUser()

  if (!user?.id) {
    return (
      <div className={styles.container}>
        <div>권한이 없습니다</div>
      </div>
    )
  }

  return children
}

export default Protected
