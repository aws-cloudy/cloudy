import React from 'react'
import styles from './LearningTagListItem.module.scss'
import { FaCheck } from 'react-icons/fa6'
import { IFilter } from '@/types/learning'
import { useSetFilter } from '@/hooks/useSetFilter'

const LearningTagListItem = (props: { item: IFilter }) => {
  const { item } = props

  const setFilter = useSetFilter(item)

  return (
    <div className={styles.container} onClick={e => setFilter()}>
      <FaCheck color="#FF9900" />
      <span>{item.name}</span>
    </div>
  )
}

export default LearningTagListItem
