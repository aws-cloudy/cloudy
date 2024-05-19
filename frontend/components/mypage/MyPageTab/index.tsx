import styles from './MyPageTab.module.scss'
import { signOut } from 'next-auth/react'
import { useTokenActions } from '@/stores/authStore'

const MyPageTab = ({ selectedTab, setSelectedTab }: any) => {
  const { resetUser } = useTokenActions()

  return (
    <>
      <div
        className={selectedTab === 'account' ? styles.activeButton : styles.button}
        onClick={() => setSelectedTab('account')}
      >
        <div>계정관리</div>
        <div>{'>'}</div>
      </div>
      <div
        className={selectedTab === 'activity' ? styles.activeButton : styles.button}
        onClick={() => setSelectedTab('activity')}
      >
        <div>활동내역</div>
        <div>{'>'}</div>
      </div>
      <div
        className={selectedTab === 'favorites' ? styles.activeButton : styles.button}
        onClick={() => setSelectedTab('favorites')}
      >
        <div>찜한 로드맵</div>
        <div>{'>'}</div>
      </div>
      <div
        className={styles.logout}
        onClick={() => {
          resetUser()
          return signOut({ callbackUrl: '/' })
        }}
      >
        로그아웃
      </div>
    </>
  )
}

export default MyPageTab
