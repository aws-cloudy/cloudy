import { getUser } from '@/utils/getUser'
import styles from './MainLectureRecom.module.scss'
import { fetchLearningRecom, fetchLearningRecomWithoutJob } from '@/apis/mainLearing'
import { ILearningCard } from '@/types/learning'
import LearningCard from '@/components/common/LearningCard'
import { jobOptions } from '@/constants/user'

async function MainLectureRecom() {
  const user = await getUser()
  const isLogin = user?.id
  const learningList = isLogin
    ? ((await fetchLearningRecom(user.jobId)) as ILearningCard[])
    : ((await fetchLearningRecomWithoutJob()) as ILearningCard[])
  const len = learningList.length

  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <div className={styles.titleBox}>
          <p className={styles.titleBoxItem1}>
            {isLogin ? `${jobOptions[user.jobId - 1].label}와 관련된 강의` : '이런 강의도 있어요'}
          </p>
          <p className={styles.titleBoxItem2}>
            {isLogin
              ? `${jobOptions[user.jobId - 1].label} 직무에 관심이 있는 사람들은 이런 강의를 들었어요`
              : 'Cloudy에서는 이런 강의도 제공하고 있어요'}
          </p>
        </div>
        <div className={styles.recomBox}>
          <div className={styles.recomBoxInner} style={{ width: `${len * 25}%` }}>
            {learningList &&
              learningList.map(each => (
                <div className={styles.recomBoxInnerItem} key={each.learningId}>
                  <LearningCard item={each} layout="grid" />
                </div>
              ))}
          </div>
        </div>
      </div>
    </div>
  )
}

export default MainLectureRecom
