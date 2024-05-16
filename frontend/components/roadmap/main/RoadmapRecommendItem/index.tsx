import React from 'react'
import styles from './RoadmapRecommendItem.module.scss'
import { IRoadmapCard } from '@/types/roadmap'
import Image from 'next/image'
import { getShortText } from '@/utils/getShortText'
import { useRouter } from 'next/navigation'

const RoadmapRecommendItem = ({ item }: { item: IRoadmapCard }) => {
  const router = useRouter()

  const onClick = () => router.push(`/roadmap/${item.roadmapId}`)

  const loadError = (e: React.SyntheticEvent<HTMLImageElement, Event>) => {
    const target = e.target as HTMLElement
    target.setAttribute('src', '/img/default.jpeg')
  }

  return (
    <div className={styles.container} onClick={onClick}>
      <Image src={item.thumbnail} alt={item.title} fill priority sizes="auto" onError={loadError} />
      <div className={styles.hoverWrap}>
        <div className={styles.title}>{item.title}</div>
        <div>{getShortText(item.summary, 80)}</div>
      </div>
    </div>
  )
}

export default RoadmapRecommendItem
