'use client'
import { useSession } from 'next-auth/react'
import styles from './page.module.scss'
import MyPageTab from '@/components/mypage/MyPageTab'
import { useEffect, useState } from 'react'
import Account from '@/components/mypage/Account'
import Activity from '@/components/mypage/Activity'
import Favorites from '@/components/mypage/Favorites'

const MyPage = () => {
  const { data: session } = useSession()
  const [selectedTab, setSelectedTab] = useState('account')

  useEffect(() => {
    console.log('유저', session?.user)
  })

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
          {selectedTab === 'account' && <Account user={session?.user} />}
          {selectedTab === 'activity' && <Activity />}
          {selectedTab === 'favorites' && <Favorites />}
        </div>
      </section>
    </>
  )
}

export default MyPage
