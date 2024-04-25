import React from 'react'
import styles from './LearningTagListItem.module.scss'
import { FaCheck } from 'react-icons/fa6'
import { IFilter } from '@/types/learning'
import { useLearningActions } from '@/stores/learning'

const LearningTagListItem = (props: { item: IFilter }) => {
  const { item } = props

  const { setJobFilter, setServiceFilter, setTypeFilter, setDifficultyFilter } = useLearningActions()

  const onClick = () => {
    switch (item.category) {
      case 'job':
        setJobFilter(item)
        break
      case 'service':
        setServiceFilter(item)
        break
      case 'type':
        setTypeFilter(item)
        break
      case 'difficulty':
        setDifficultyFilter(item)
        break
    }
  }

  return (
    <div className={styles.container} onClick={e => onClick()}>
      <FaCheck color="#FF9900" />
      <span>{item.name}</span>
    </div>
  )
}

export default LearningTagListItem
