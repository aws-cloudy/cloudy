import React from 'react'
import styles from './LearningTagList.module.scss'
import LearningTagListItem from '../LearningTagListItem'
import { IFilter, ILearningTagList } from '@/types/learning'

const LearningTagList = ({ filter, setFilter }: ILearningTagList) => {
  const onClick = (item: IFilter) => {
    setFilter({ ...filter, [item.category]: filter[item.category].filter(v => v !== item) })
  }

  return (
    <div className={styles.container}>
      {filter &&
        [...filter.job, ...filter.service, ...filter.type, ...filter.difficulty].map((v, i) => (
          <LearningTagListItem key={i} item={v} onClick={() => onClick(v)} />
        ))}
    </div>
  )
}

export default LearningTagList
