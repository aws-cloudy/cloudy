import React from 'react'
import styles from './LearningInput.module.scss'

const LearningInput = () => {
  return (
    <>
      <input className={styles.input} placeholder="무엇이든 검색해보세요 ..." />
    </>
  )
}

export default LearningInput
