import React from 'react'
import styles from './LearningTagList.module.scss'
import LearningTagListItem from '../LearningTagListItem'
import { useDifficultyFilter, useServiceFilter, useTypeFilter, usejobFilter } from '@/stores/learning'

const LearningTagList = () => {
  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const dificulties = useDifficultyFilter()

  return (
    <div className={styles.container}>
      {[...jobs, ...services, ...types, ...dificulties].map(v => (
        <LearningTagListItem key={v.name} item={v} />
      ))}
    </div>
  )
}

export default LearningTagList
