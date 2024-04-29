'use client'

import React, { useEffect, useState } from 'react'
import styles from './LearningList.module.scss'
import LearningCard from '@/components/common/LearningCard'
import { useLearninglayout } from '@/stores/search'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { ILearningCard } from '@/types/learning'
import { LEARNING_ROWS_PER_PAGE } from '@/constants/rows'
import { getLearnings } from '@/apis/learning'

const LearningList = (props: { data: ILearningCard[] }) => {
  const { data } = props

  // 무한 스크롤
  const [offset, setOffset] = useState<number>(2)
  const [list, setList] = useState<ILearningCard[]>(data)

  const loadMoreLearning = async () => {
    const apiLearnings = await getLearnings(offset, LEARNING_ROWS_PER_PAGE)
    setList([...list, ...apiLearnings])
    setOffset(offset + 1)
  }

  // 반응형 width 감지
  const { isTablet } = useResponsiveWidth()
  const [layout, setLayout] = useState<'grid' | 'justify'>('grid')
  const learningLayout = useLearninglayout()

  useEffect(() => {
    isTablet ? setLayout('justify') : setLayout(learningLayout)
  }, [isTablet, layout, learningLayout])

  return (
    <div className={layout === 'grid' ? styles.gridContainer : styles.justifyContainer}>
      {data && data.map(item => <LearningCard key={item.learningId} item={item} layout={layout} />)}
      <button onClick={loadMoreLearning}>Load more</button>
    </div>
  )
}

export default LearningList
