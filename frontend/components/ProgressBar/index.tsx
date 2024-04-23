'use client'
import React from 'react'
import styles from './index.module.scss'

const ProgressBar = ({ currentStep, totalSteps }: any) => {
  const progressPercentage = (currentStep / totalSteps) * 100

  return (
    <div className={styles.progressBarContainer}>
      <div className={styles.firstCircle} />
      <div className={styles.progressBarLine} style={{ width: `${progressPercentage}%` }} />
      <div
        className={styles.lastCircle}
        style={{ backgroundColor: progressPercentage === 100 ? '#ff9900' : '#d9d9d9' }}
      />
    </div>
  )
}

export default ProgressBar
