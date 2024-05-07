import { getMainRoadmaps } from '@/apis/roadmap'
import MainRoadmapRecomItem from '../MainRoadmapRecomItem'
import styles from './MainRoadmapRecom.module.scss'

import { FaChevronLeft } from 'react-icons/fa6'
import { FaChevronRight } from 'react-icons/fa6'
import { IRoadmapCard } from '@/types/roadmap'

async function MainRoadmapRecom() {
  const roadmaps = (await getMainRoadmaps()) as IRoadmapCard[]

  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <div className={styles.titleBox}>
          <p className={styles.title}>
            AWS <span>100%</span> 활용하기
          </p>
          <div className={styles.border} />
          <div className={styles.buttonBox}>
            <FaChevronLeft className={styles.button} />
            <FaChevronRight className={styles.button} />
          </div>
        </div>
        <div className={styles.roadmapBox}>
          {roadmaps && roadmaps.map((e, i) => <MainRoadmapRecomItem key={i} title={e.title} content={e.summary} />)}
        </div>
      </div>
    </div>
  )
}

export default MainRoadmapRecom
