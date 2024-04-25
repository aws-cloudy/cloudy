import { useState } from 'react'
import styles from './Activity.module.scss'
import ActivityWrite from './ActivityWrite'
import ActivityComment from './ActivityComment/indext'
import Dropdown from '@/components/common/Dropdown'

const Activity = () => {
  const [selectedTab, setSelectedTab] = useState('write')

  //작성글 더미데이터
  const wData = [
    {
      id: 1,
      title: '이거 왜 오류난건가요?',
      status: '미해결',
      context:
        '강의 내용대로 하고 있었는데 아래와 같은 오류가 발생했습니다. 설정이 잘못된 걸까요? 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문',
      tags: ['S3', 'Bedrock'],
    },
    {
      id: 2,
      title: '이거 왜 오류난건가요?',
      status: '해결',
      context:
        '강의 내용대로 하고 있었는데 아래와 같은 오류가 발생했습니다. 설정이 잘못된 걸까요? 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문',
      tags: ['S3', 'Bedrock'],
    },
  ]

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
      <div className={styles.row}>
        <Dropdown placeholder="작성일순" width={130} height={30} />
        <input type="text" className={styles.input} />
      </div>
      {selectedTab === 'write' && <ActivityWrite posts={wData} />}
      {selectedTab === 'comment' && <ActivityComment />}
    </section>
  )
}

export default Activity
