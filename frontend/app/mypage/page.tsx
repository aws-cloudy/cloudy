'use client'
import { useSession } from 'next-auth/react'
import styles from './page.module.scss'
import MyPageTab from '@/components/mypage/MyPageTab'
import { useState } from 'react'

const MyPage = () => {
  const { data: session } = useSession()
  const [selectedTab, setSelectedTab] = useState('account')

  return (
    <>
      <section className={styles.section}>
        <div className={styles.left}>
          <div className={styles.intro}>
            안녕하세요
            <br />
            {session?.user?.name}님!
          </div>
          <MyPageTab selectedTab={selectedTab} setSelectedTab={setSelectedTab} />
        </div>
        <div className={styles.right}>
          {selectedTab === 'account' && <div>계정 관리 컴포넌트</div>}
          {selectedTab === 'activity' && <div>활동 내역 컴포넌트</div>}
          {selectedTab === 'favorites' && <div>찜한 로드맵 컴포넌트</div>}
        </div>
      </section>
    </>
  )
}

export default MyPage
