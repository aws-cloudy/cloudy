import styles from './LearningRecommendItem.module.scss'
import React from 'react'
import Image from 'next/image'
import { getShortText } from '@/utils/getShortText'
import { ILearningCard } from '@/types/learning'
import { getDifficulty } from '@/utils/getDifficulty'
import { useRouter } from 'next/navigation'

const LearningRecommendItem = ({ item }: { item: ILearningCard }) => {
  const router = useRouter()

  const difficulty = getDifficulty(item.difficulty)

  const onClick = () => router.push(item.link)

  return (
    <div className={styles.container} onClick={onClick}>
      <div className={styles.imgWrap}>
        <Image src={item.thumbnail} alt={item.title} fill priority sizes="auto" className={styles.img} />
        <div className={`${styles.badge} ${difficulty.class}`}>{difficulty.text}</div>
      </div>

      <div className={styles.title}>{item.title}</div>
      <div className={styles.hoverWrap}>
        <div>{item.summary && getShortText(item.summary, 100)}</div>
      </div>
    </div>
  )
}

export default LearningRecommendItem
