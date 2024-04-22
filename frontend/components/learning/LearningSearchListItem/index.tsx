import React from 'react'
import styles from './LearningSearchListItem.module.scss'
import { IoMdSearch } from 'react-icons/io'
import { ILearningSearchListItem } from '@/types/learning'

const LearningSearchListItem = (props: ILearningSearchListItem) => {
  const { onClick } = props

  const itemClick = (e: React.MouseEvent) => {
    e.preventDefault()
    onClick('keyword dummy')
  }

  return (
    <div className={styles.container} onClick={itemClick}>
      <IoMdSearch size="1.4em" />
      <span className={styles.text}>자동완성</span>
    </div>
  )
}

export default LearningSearchListItem
