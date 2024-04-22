'use client'

import React, { useState } from 'react'
import styles from './LearningSection.module.scss'
import LearningInput from '../LearningInput'
import LearningSearchList from '../LearningSearchList'

const LearningSection = () => {
  const [value, setValue] = useState<string>('') // input value
  const [keyword, setKeyword] = useState<string>('') // 검색어

  return (
    <section className={styles.section}>
      <LearningInput value={value} setValue={setValue} setKeyword={setKeyword} />
      {value && keyword === '' && <LearningSearchList />}
      <div>input area</div>
    </section>
  )
}

export default LearningSection
;<div>ss</div>
