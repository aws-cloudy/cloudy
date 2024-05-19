import React from 'react'
import styles from './LearningSearchListItem.module.scss'
import { IoMdSearch } from 'react-icons/io'
import { ILearningSearchListItem } from '@/types/learning'

const LearningSearchListItem = (props: ILearningSearchListItem) => {
  const { onClick, item, selected } = props

  return (
    <div className={`${styles.container} ${selected && styles.selected}`} onMouseDown={onClick}>
      <IoMdSearch size="1.4em" />
      <span className={styles.text}>{item.title}</span>
    </div>
  )
}

export default LearningSearchListItem
