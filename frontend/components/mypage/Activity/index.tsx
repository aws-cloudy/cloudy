import { useState } from 'react'
import styles from './Activity.module.scss'
import ActivityWrite from './ActivityWrite'
import ActivityComment from './ActivityComment/indext'

const Activity = () => {
  const [selectedTab, setSelectedTab] = useState('write')

  return (
    <section className={styles.section}>
      <div className={styles.intro}>활동내역</div>
      <div className={styles.tab}>
        <div
          className={selectedTab === 'write' ? styles.activeTab : styles.inActiveTab}
          onClick={() => {
            setSelectedTab('write')
          }}
        >
          작성한 글
        </div>
        <div
          className={selectedTab === 'comment' ? styles.activeTab : styles.inActiveTab}
          onClick={() => {
            setSelectedTab('comment')
          }}
        >
          작성한 댓글
        </div>
      </div>
      {selectedTab === 'write' && <ActivityWrite />}
      {selectedTab === 'comment' && <ActivityComment />}
    </section>
  )
}

export default Activity
