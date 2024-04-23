import React from 'react'
import styles from './LearningList.module.scss'
import { learningData } from './learningData'
import LearningItem from '@/components/common/LearningItem'
import { useLearninglayout } from '@/stores/search'

const LearningList = () => {
  const layout = useLearninglayout()

  return (
    <div className={layout === 'grid' ? styles.gridContainer : styles.justifyContainer}>
      {learningData.map(item => (
        <LearningItem key={item.id} item={item} layout={layout} />
      ))}
    </div>
  )
}

export default LearningList
