import MainIntroduceItem from '../MainIntroduceItem'
import styles from './MainIntroduce.module.scss'
import { PiChalkboardTeacher } from 'react-icons/pi'
import { PiStudent } from 'react-icons/pi'
import { PiCertificate } from 'react-icons/pi'
import { CiCloudOn } from 'react-icons/ci'

function MainIntroduce() {
  return (
    <div className={styles.container}>
      <div className={styles.titleBox}>
        <p className={styles.titleBoxItem1}>AWS 기초 강의부터 자격증까지</p>
        <p className={styles.titleBoxItem2}>클라우디와 함께라면 가능하니까</p>
      </div>
      <div className={styles.itemBox}>
        <div className={styles.itemBoxInner}>
          <MainIntroduceItem title="AWS 강의" student="100+" Icon={PiChalkboardTeacher} />
          <MainIntroduceItem title="수강생" student="100+" Icon={PiStudent} />
          <MainIntroduceItem title="자격증 강의" student="100+" Icon={PiCertificate} />
        </div>
      </div>
      <CiCloudOn className={styles.bgIcon} />
    </div>
  )
}

export default MainIntroduce
