import PageTitle from '@/components/common/PageTitle'
import styles from './page.module.scss'
import CommunitySidebar from '@/components/community/main/CommunitySidebar'
import CommunityList from '@/components/community/main/CommunityList'

const Communitypage = () => {
  return (
    <>
      <div className={styles.container}>
        <div className={styles.content}>
          <PageTitle />
          <CommunityList />
        </div>
        <div className={styles.sidebar}>
          <CommunitySidebar />
        </div>
      </div>
    </>
  )
}

export default Communitypage
