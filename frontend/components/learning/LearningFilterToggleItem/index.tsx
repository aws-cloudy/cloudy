import React from 'react'
import styles from './LearningFilterToggleItem.module.scss'
import { FiPlus } from 'react-icons/fi'
import { FiMinus } from 'react-icons/fi'
import { IFilter } from '@/types/learning'
import { useDifficultyFilter, useServiceFilter, useTypeFilter, usejobFilter } from '@/stores/learning'
import { useSetFilter } from '@/hooks/useSetFilter'

const LearningFilterToggoleItem = (props: { item: IFilter }) => {
  const { item } = props

  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const dificulties = useDifficultyFilter()

  const setFilter = useSetFilter(item)

  return (
    <div className={styles.container} onClick={e => setFilter()}>
      <div className={styles.wrap}>
        {[...jobs, ...services, ...types, ...dificulties].every(v => v.name !== item.name) ? (
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
