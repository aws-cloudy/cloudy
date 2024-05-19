import React from 'react'
import styles from './LearningTagListItem.module.scss'
import { FaCheck } from 'react-icons/fa6'
import { IFilter } from '@/types/learning'

const LearningTagListItem = (props: { item: IFilter; onClick: () => void }) => {
  const { item, onClick } = props

  return (
    <div className={styles.container} onClick={onClick}>
      <FaCheck color="#FF9900" />
      <span>{item.name}</span>
    </div>
  )
}

export default LearningTagListItem
