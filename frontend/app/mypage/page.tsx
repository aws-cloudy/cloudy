'use client'
import { signIn, useSession } from 'next-auth/react'
import styles from './page.module.scss'
import MyPageTab from '@/components/mypage/MyPageTab'
import { useEffect, useState } from 'react'
import Account from '@/components/mypage/Account'
import Activity from '@/components/mypage/Activity'
import Favorites from '@/components/mypage/Favorites'
import Loading from '@/components/common/Loading'

const MyPage = () => {
  const { data: session, status } = useSession()
  const [selectedTab, setSelectedTab] = useState('account')

  useEffect(() => {
    console.log(session)
    if (status === 'unauthenticated') {
      alert('로그인이 필요한 페이지입니다.')
      signIn('cognito', { callbackUrl: '/' })
    }
  }, [session, status])

  if (status === 'loading') {
    return <Loading />
  } else if (status === 'authenticated') {
    return (
      <>
        <section className={styles.section}>
          <div className={styles.left}>
            <div className={styles.intro}>
              안녕하세요.
              <br />
              {session.user.name}님
            </div>
            <MyPageTab selectedTab={selectedTab} setSelectedTab={setSelectedTab} />
          </div>
          <div className={styles.right}>
            {selectedTab === 'account' && session && <Account user={session.user} />}
            {selectedTab === 'activity' && session && <Activity user={session.user} />}
            {selectedTab === 'favorites' && session && <Favorites user={session.user} />}
          </div>
        </section>
      </>
    )
  }
}

export default MyPage
