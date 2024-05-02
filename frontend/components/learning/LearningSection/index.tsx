'use client'

import React, { useState } from 'react'
import styles from './LearningSection.module.scss'
import LearningInput from '../LearningInput'
import LearningSearchResult from '../LearningSearchResult'
import LearningList from '../LearningList'

const LearningSection = () => {
  const [value, setValue] = useState<string>('') // input value
  const [keyword, setKeyword] = useState<string>('') // 검색어

  return (
    <section className={styles.section}>
      <LearningInput value={value} setValue={setValue} keyword={keyword} setKeyword={setKeyword} />
      <LearningSearchResult keyword={keyword} />
      {/* <LearningRecommend /> */}
      <LearningList keyword={keyword} />
    </section>
  )
}

export default LearningSection
