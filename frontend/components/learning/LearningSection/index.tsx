'use client'

import React, { useState } from 'react'
import styles from './LearningSection.module.scss'
import LearningInput from '../LearningInput'
import LearningSearchResult from '../LearningSearchResult'
import LearningList from '../LearningList'

const LearningSection = () => {
  const [value, setValue] = useState<string>('') // input value

  return (
    <section className={styles.section}>
      <LearningInput value={value} setValue={setValue} />
      <LearningSearchResult />
      <LearningList />
    </section>
  )
}

export default LearningSection
