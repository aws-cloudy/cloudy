import { getUser } from '@/utils/getUser'
import styles from './MainLectureRecom.module.scss'
import { fetchLearningRecomWithoutJob } from '@/apis/mainLearing'
import { ILearningCard } from '@/types/learning'
import LearningCard from '@/components/common/LearningCard'

async function MainLectureRecom() {
  const user = await getUser()
  const isLogin = user?.id
  const learningList = (await fetchLearningRecomWithoutJob()) as ILearningCard[]

  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <div className={styles.titleBox}>
          <p className={styles.titleBoxItem1}>{isLogin ? '~와 관련된 강의' : '이런 강의도 있어요'}</p>
          <p className={styles.titleBoxItem2}>Cloudy에서는 이런 강의도 제공하고 있어요</p>
        </div>
        <div className={styles.recomBox}>
          <div className={styles.recomBoxInner}>
            {learningList && learningList.map(each => <LearningCard key={each.learningId} item={each} layout="grid" />)}
          </div>
        </div>
      </div>
    </div>
  )
}

export default MainLectureRecom
