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

  const cData = [
    {
      id: 1,
      category: '커뮤니티',
      context:
        '넵!! 해결되었습니다. 잘 짚어주셔서 감사합니다. 해당 부분 고려해서 수정해보도록 하겠습니다. 혹시 다른 부분도 여쭤봐도 될까요?',
      writer: '김싸피',
      date: '2024.04.15 11:53',
    },
    {
      id: 2,
      category: '로드맵',
      context: '정말 마음에 드는 로드맵이네요 ~!',
      writer: '클라우디',
      date: '2024.04.12 14:20',
    },
    {
      id: 3,
      category: '커뮤니티',
      context:
        '넵!! 해결되었습니다. 잘 짚어주셔서 감사합니다. 해당 부분 고려해서 수정해보도록 하겠습니다. 혹시 다른 부분도 여쭤봐도 될까요?',
      writer: '김싸피',
      date: '2024.04.08 16:03',
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
        <input type="text" placeholder="검색어를 입력해주세요." className={styles.input} />
      </div>
      {selectedTab === 'write' && <ActivityWrite posts={wData} />}
      {selectedTab === 'comment' && <ActivityComment comments={cData} />}
    </section>
  )
}

export default Activity
