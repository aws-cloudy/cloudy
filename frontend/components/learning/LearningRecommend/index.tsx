import React from 'react'
import styles from './LearningRecommend.module.scss'
import LearningItem from '@/components/common/LearningItem'
import { learningData } from '../LearningList/learningData'

const LearningRecommend = () => {
  return (
    <div className={styles.container}>
      <div className={styles.title}>Oudy Pick!</div>
      <div className={styles.desc}>우디가 회원님께 추천드리는 강의 입니다.</div>
      <LearningItem item={learningData[0]} layout="justify" />
    </div>
  )
}

export default LearningRecommend
