'use client'

import React, { useEffect, useState } from 'react'
import styles from './LearningList.module.scss'
import { learningData } from './learningData'
import LearningItem from '@/components/common/LearningItem'
import { useLearninglayout, useSearchActions } from '@/stores/search'
import { useMediaQuery } from 'react-responsive'

const LearningList = () => {
  // 반응형 width 감지
  const isTablet = useMediaQuery({ query: '(max-width: 992px)' })
  const [layout, setLayout] = useState<'grid' | 'justify'>('grid')
  const learningLayout = useLearninglayout()

  useEffect(() => {
    isTablet ? setLayout('justify') : setLayout(learningLayout)
  }, [isTablet, layout, learningLayout])

  return (
    <div className={layout === 'grid' ? styles.gridContainer : styles.justifyContainer}>
      {learningData.map(item => (
        <LearningItem key={item.id} item={item} layout={layout} />
      ))}
    </div>
  )
}

export default LearningList
