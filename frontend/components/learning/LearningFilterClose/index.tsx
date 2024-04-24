import React from 'react'
import { ILearningFilterClose } from '@/types/learning'
import styles from './LearningFilterClose.module.scss'

const LearningFilterClose = (props: ILearningFilterClose) => {
  const { openFilter } = props

  return (
    <div className={styles.container} onClick={e => openFilter()}>
      <div className={styles.text}>open filter</div>
    </div>
  )
}

export default LearningFilterClose
