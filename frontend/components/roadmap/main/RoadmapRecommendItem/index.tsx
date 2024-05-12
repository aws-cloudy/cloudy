import React from 'react'
import styles from './RoadmapRecommendItem.module.scss'
import { IRoadmapCard } from '@/types/roadmap'
import Image from 'next/image'
import { getShortText } from '@/utils/getShortText'

const RoadmapRecommendItem = ({ item }: { item: IRoadmapCard }) => {
  return (
    <div className={styles.container}>
      <Image src={item.thumbnail} alt={item.title} fill priority sizes="auto" />
      <div className={styles.hoverWrap}>
        <div className={styles.title}>{item.title}</div>
        <div>{getShortText(item.summary, 80)}</div>
      </div>
    </div>
  )
}

export default RoadmapRecommendItem