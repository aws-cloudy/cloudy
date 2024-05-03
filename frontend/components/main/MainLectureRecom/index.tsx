import MainLectureRecomItems from '../MainLectureRecomItems'
import styles from './MainLectureRecom.module.scss'

function MainLectureRecom() {
  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <div className={styles.titleBox}>
          <p className={styles.titleBoxItem1}>"직무명"와 관련된 강의</p>
          <p className={styles.titleBoxItem2}>나와 같은 직무에 종사중인 사람들은 이런 강의를 들었어요</p>
        </div>
        <MainLectureRecomItems />
      </div>
    </div>
  )
}

export default MainLectureRecom
