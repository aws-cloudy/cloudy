import React from 'react'
import styles from './LearningItem.module.scss'
import { ILearningItem } from '@/types/learning'
import { getDifficulty } from '@/utils/getDifficulty'

const LearningItem = (props: { item: ILearningItem }) => {
  const { item } = props

  const difficulty = getDifficulty(item.difficulty)

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
        <button className={styles.learnButton}>더 알아보기</button>
      </div>
    </div>
  )
}

export default LearningItem
