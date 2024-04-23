import React from 'react'
import styles from './LearningList.module.scss'
import { learningData } from './learningData'

const LearningList = () => {
  return (
    <div className={styles.container}>
      {learningData.map(item => (
        <div key={item.id}>
          <div>{item.title}</div>
        </div>
      ))}
    </div>
  )
}

export default LearningList
