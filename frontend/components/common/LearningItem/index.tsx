'use client'

import React, { useState } from 'react'
import styles from './LearningItem.module.scss'
import { ILearningItem } from '@/types/learning'
import { getDifficulty } from '@/utils/getDifficulty'

const LearningItem = (props: { item: ILearningItem }) => {
  const { item } = props

  const [more, setMore] = useState<boolean>(true)

  const difficulty = getDifficulty(item.difficulty)

  const clickMoreButton = () => setMore(!more)

  return (
    <div className={styles.container}>
      <div className={styles.imgWrap}>
        <img src={item.thumbnail} alt={item.title} className={styles.img} />
        <div className={`${styles.badge} ${difficulty.class}`}>{difficulty.text}</div>
      </div>
      <div className={styles.wrap}>
        <div className={styles.categoryWrap}>
          {item.service.map(service => (
            <div className={styles.category} key={service.id}>
              # {service.name}
            </div>
          ))}
        </div>
        <div className={styles.title}>{item.title}</div>
        {more ? (
          <button className={styles.moreButton} onClick={clickMoreButton}>
            더 알아보기
          </button>
        ) : (
          <div className={styles.moreWrap}>
            <div className={styles.duration}>소요시간 {item.duration}</div>
            <div onClick={clickMoreButton} className={styles.summary}>
              {item.summary}
            </div>
            <button className={styles.linkButton}>학습하러 가기 {'>'}</button>
          </div>
        )}
      </div>
    </div>
  )
}

export default LearningItem
