'use client'

import React from 'react'
import styles from './RoadmapRecommend.module.scss'
import { roadmapData } from '../RoadmapListSection/roadmapData'
import RoadmapRecommendItem from '../RoadmapRecommendItem'
import { useSearchParams } from 'next/navigation'
import { MdInfoOutline } from 'react-icons/md'

const RoadmapRecommend = () => {
  const params = useSearchParams()
  const keyword = params.get('query') || ''

  if (!keyword) return
  return (
    <article className={styles.container}>
      <div className={styles.title}>
        <div>존 Pick!</div>
        <div className={styles.infoWrap}>
          <MdInfoOutline color="#ff9900" cursor="pointer" />
          <p className={styles.arrowBox}>존은 로드맵을 추천해주는 클라우디의 서비스입니다</p>
        </div>
      </div>
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
