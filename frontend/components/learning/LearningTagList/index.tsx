import React from 'react'
import styles from './LearningTagList.module.scss'
import LearningTagListItem from '../LearningTagListItem'
import { IFilter } from '@/types/learning'

const LearningTagList = (props: { filters: IFilter[] }) => {
  const { filters } = props

  return (
    <div className={styles.container}>
      {filters.map(v => (
        <LearningTagListItem key={v.name} text={v.name} />
      ))}
    </div>
  )
}

export default LearningTagList
