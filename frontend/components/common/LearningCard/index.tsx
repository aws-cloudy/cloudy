'use client'

import React, { useState } from 'react'
import styles from './LearningCard.module.scss'
import { ILearningCard } from '@/types/learning'
import { getDifficulty } from '@/utils/getDifficulty'
import Image from 'next/image'

const LearningCard = (props: { item: ILearningCard; layout: string }) => {
  const { item, layout } = props

  const [more, setMore] = useState<boolean>(false)

  const difficulty = getDifficulty(item.difficulty)

  const clickMoreButton = () => layout !== 'justify' && setMore(!more)

  return (
    <div className={layout === 'grid' ? styles.container : styles.justifyContainer}>
      <div className={layout === 'grid' ? styles.imgWrap : styles.justifyImgWrap}>
        <Image src={item.thumbnail} alt={item.title} className={styles.img} fill priority sizes="auto" />
        <div className={`${styles.badge} ${difficulty.class} ${layout === 'justify' && styles.justifyBadge}`}>
          {difficulty.text}
        </div>
      </div>
      <div className={`${styles.wrap} ${styles.justifyWrap}`}>
        <div className={layout === 'grid' ? styles.categoryWrap : styles.justifyCategoryWrap}>
          {item.service.map(service => (
            <div className={styles.category} key={service.id}>
              # {service.name}
            </div>
          ))}
        </div>
        <div className={styles.title}>{item.title}</div>
        {more || layout === 'justify' ? (
          <div className={styles.moreWrap}>
            <div className={styles.duration}>소요시간 {item.duration}</div>
            <div onClick={clickMoreButton} className={styles.summary}>
              {item.summary}
            </div>
            <button className={styles.linkButton}>학습하러 가기 {'〉'}</button>
          </div>
        ) : (
          <button className={styles.moreButton} onClick={clickMoreButton}>
            더 알아보기
          </button>
        )}
      </div>
    </div>
  )
}

export default LearningCard
