'use client'

import React, { useState } from 'react'
import styles from './LearningSection.module.scss'
import LearningInput from '../LearningInput'
import LearningSearchResult from '../LearningSearchResult'
import LearningList from '../LearningList'
import LearningRecommend from '../LearningRecommend'
import { ILearningCard } from '@/types/learning'

const LearningSection = (props: { data: ILearningCard[] }) => {
  const { data } = props

  const [value, setValue] = useState<string>('') // input value
  const [keyword, setKeyword] = useState<string>('') // 검색어

  return (
    <section className={styles.section}>
      <LearningInput value={value} setValue={setValue} keyword={keyword} setKeyword={setKeyword} />
      <LearningSearchResult keyword={keyword} />
      <LearningRecommend />
      <LearningList data={data} />
    </section>
  )
}

export default LearningSection
