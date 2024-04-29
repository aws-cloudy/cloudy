import React, { useEffect, useState } from 'react'
import styles from './LearningList.module.scss'
import { learningData } from './learningData'
import LearningCard from '@/components/common/LearningCard'
import { useLearninglayout } from '@/stores/search'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { ILearningCard } from '@/types/learning'

const LearningList = (props: { data: ILearningCard[] }) => {
  const { data } = props

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
    </div>
  )
}

export default LearningList
