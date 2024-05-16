'use client'
import { signIn, useSession } from 'next-auth/react'
import styles from './page.module.scss'
import MyPageTab from '@/components/mypage/MyPageTab'
import { useEffect, useState } from 'react'
import Account from '@/components/mypage/Account'
import Activity from '@/components/mypage/Activity'
import Favorites from '@/components/mypage/Favorites'
import Loading from '@/components/common/Loading'
import { getBookmarks } from '@/apis/bookmark'
import { IRoadmapCard } from '@/types/roadmap'

const MyPage = () => {
  const { data: session, status } = useSession()
  const [selectedTab, setSelectedTab] = useState('account')
  const [bookmarksData, setBookmarksData] = useState<IRoadmapCard[]>([])

  useEffect(() => {
    console.log(session)
    if (status === 'unauthenticated') {
      alert('로그인이 필요한 페이지입니다.')
      signIn('cognito', { callbackUrl: '/' })
    } else if (status === 'authenticated') {
      console.log('memberId: ', session.user.id)

      if (session.user.id) {
        getBookmarks(session.user.id)
          .then(data => setBookmarksData(data.roadmaps))
          .catch(error => console.error('북마크 로드 실패', error))
      }
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
            {selectedTab === 'favorites' && session && <Favorites bookmarksData={bookmarksData} />}
          </div>
        </section>
      </>
    )
  }
}

export default MyPage
