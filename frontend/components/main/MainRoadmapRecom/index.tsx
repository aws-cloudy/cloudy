'use client'

import { getMainRoadmaps } from '@/apis/roadmap'
import MainRoadmapRecomItem from '../MainRoadmapRecomItem'
import styles from './MainRoadmapRecom.module.scss'

import { FaChevronLeft } from 'react-icons/fa6'
import { FaChevronRight } from 'react-icons/fa6'
import { IRoadmapCard } from '@/types/roadmap'
import { useEffect, useState } from 'react'

function MainRoadmapRecom() {
  const [roadmaps, setRoadmaps] = useState<IRoadmapCard[]>([])
  const [page, setPage] = useState(0)

  const getRoadmaps = async () => {
    const pg = page ? page : null
    const res = await getMainRoadmaps(pg)
    setRoadmaps(res)
  }

  useEffect(() => {
    getRoadmaps()
  }, [page])

  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <div className={styles.titleBox}>
          <p className={styles.title}>
            AWS <span>100%</span> 활용하기
          </p>
          <div className={styles.border} />
          <div className={styles.buttonBox}>
            <FaChevronLeft
              className={`${styles.button} ${page === 0 && styles.buttonDisabled}`}
              onClick={() => {
                setPage(prev => (prev > 0 ? prev - 1 : 0))
              }}
            />
            <FaChevronRight
              className={`${styles.button} ${page === 2 && styles.buttonDisabled}`}
              onClick={() => {
                setPage(prev => (prev < 2 ? prev + 1 : 2))
              }}
            />
          </div>
        </div>
        <div className={styles.roadmapBox}>
          {roadmaps &&
            roadmaps.map((e, i) => (
              <MainRoadmapRecomItem key={i} title={e.title} content={e.summary} id={e.roadmapId} imgSrc={e.thumbnail} />
            ))}
        </div>
      </div>
    </div>
  )
}

export default MainRoadmapRecom
