import { IRoadmapCard } from '@/types/roadmap'
import styles from './LearningRecommendItem.module.scss'
import React from 'react'
import Image from 'next/image'
import { getShortText } from '@/utils/getShortText'

const LearningRecommendItem = ({ item }: { item: IRoadmapCard }) => {
  return (
    <div className={styles.container}>
      <Image src={item.thumbnail} alt={item.title} fill priority sizes="auto" />
      <div className={styles.hoverWrap}>
        <div className={styles.title}>{item.title}</div>
        <div>{getShortText(item.summary, 40)}</div>
      </div>
    </div>
  )
}

export default LearningRecommendItem
