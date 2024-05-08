import React from 'react'
import styles from './LearningSearchList.module.scss'
import LearningSearchListItem from '../LearningSearchListItem'
import { ILearningSearchList } from '@/types/learning'

const LearningSearchList = (props: ILearningSearchList) => {
  const { setValue } = props

  // 자동 검색어 클릭시
  const onClick = (v: string) => {
    setValue(v)
  }

  return (
    <div className={styles.container}>
      <LearningSearchListItem onClick={onClick} />
      <LearningSearchListItem onClick={onClick} />
      <LearningSearchListItem onClick={onClick} />
    </div>
  )
}

export default LearningSearchList
