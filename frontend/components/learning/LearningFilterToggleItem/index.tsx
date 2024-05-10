import React from 'react'
import styles from './LearningFilterToggleItem.module.scss'
import { FiPlus } from 'react-icons/fi'
import { FiMinus } from 'react-icons/fi'
import { ILearningFilterToggoleItem } from '@/types/learning'

const LearningFilterToggoleItem = (props: ILearningFilterToggoleItem) => {
  const { item, list, setList, filter } = props

  const onClick = () => {
    const index = list.indexOf(item)
    index === -1 ? setList([...list, item]) : setList(list.filter(v => v != item))
  }

  return (
    <div className={styles.container} onClick={e => onClick()}>
      <div className={styles.wrap}>
        {filter.every(v => v.name !== item.name) ? (
          <FiPlus data-testid="plus-icon" />
        ) : (
          <FiMinus data-testid="minus-icon" />
        )}
        <span>{item.name}</span>
      </div>
    </div>
  )
}

export default LearningFilterToggoleItem
