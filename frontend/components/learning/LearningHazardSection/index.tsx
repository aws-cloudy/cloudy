import React from 'react'
import styles from './LearningHazardSection.module.scss'
import Hazard from '@/components/common/Hazard'

const LearningHazard = () => {
  return (
    <section className={styles.section}>
      <Hazard />
    </section>
  )
}

export default LearningHazard
