import React from 'react'
import styles from './LearningRecommend.module.scss'
import { MdInfoOutline } from 'react-icons/md'
import { useSearchParams } from 'next/navigation'
import LearningRecommendItem from '../LearningRecommendItem'
import { roadmapData } from '@/components/roadmap/main/RoadmapListSection/roadmapData'

const LearningRecommend = () => {
  const params = useSearchParams()
  const keyword = params.get('query') || params.get('oquery') || ''

  if (!keyword) return
  return (
    <article className={styles.container}>
      <div className={styles.title}>
        <div>우디 Pick!</div>
        <div className={styles.infoWrap}>
          <MdInfoOutline color="#ff9900" cursor="pointer" />
          <p className={styles.arrowBox}>우디는 강의를 추천해주는 클라우디의 서비스입니다</p>
        </div>
      </div>
      <div className={styles.desc}>{`'${keyword}'와 관련된 추천 강의입니다`}</div>
      <div className={styles.wrap}>
        {roadmapData.map(item => (
          <LearningRecommendItem key={item.roadmapId} item={item} />
        ))}
      </div>
    </article>
  )
}

export default LearningRecommend
