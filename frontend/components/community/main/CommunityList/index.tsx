import CommunityListItem from '../CommunityListItem'
import { dummyQuestion } from '../dummy'
import styles from './CommunityList.module.scss'

function CommunityList() {
  return (
    <div className={styles.container}>
      <p className={styles.result}>총 {dummyQuestion.length}개의 질문이 등록되어 있습니다.</p>
      {dummyQuestion.map(e => (
        <CommunityListItem key={e.id} question={e} />
      ))}
    </div>
  )
}

export default CommunityList
