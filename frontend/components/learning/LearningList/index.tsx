import React from 'react'
import styles from './LearningList.module.scss'
import { learningData } from './learningData'
import LearningItem from '@/components/common/LearningItem'

const LearningList = () => {
  return (
    <div className={styles.container}>
      {learningData.map(item => (
        <LearningItem key={item.id} item={item} />
      ))}
    </div>
  )
}

export default LearningList
