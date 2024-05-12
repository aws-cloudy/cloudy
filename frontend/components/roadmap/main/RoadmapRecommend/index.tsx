'use client'

import React from 'react'
import styles from './RoadmapRecommend.module.scss'
import { roadmapData } from '../RoadmapListSection/roadmapData'
import RoadmapRecommendItem from '../RoadmapRecommendItem'
import { useSearchParams } from 'next/navigation'

const RoadmapRecommend = () => {
  const params = useSearchParams()
  const keyword = params.get('query') || ''

  if (!keyword) return
  return (
    <article className={styles.container}>
      <div className={styles.title}>존 Pick!</div>
      <div className={styles.desc}>{`'${keyword}'와 관련된 추천 로드맵입니다`}</div>
      <div className={styles.wrap}>
        {roadmapData.map(item => (
          <RoadmapRecommendItem key={item.roadmapId} item={item} />
        ))}
      </div>
    </article>
  )
}

export default RoadmapRecommend
