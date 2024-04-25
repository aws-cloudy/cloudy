import React from 'react'
import styles from './LearningFilterToggleItem.module.scss'
import { FiPlus } from 'react-icons/fi'
import { FiMinus } from 'react-icons/fi'
import { IFilter } from '@/types/learning'
import { useLearningActions } from '@/stores/learning'
import { useDifficultyFilter, useServiceFilter, useTypeFilter, usejobFilter } from '@/stores/learning'

const LearningFilterToggoleItem = (props: { item: IFilter }) => {
  const { item } = props

  const { setJobFilter, setServiceFilter, setTypeFilter, setDifficultyFilter } = useLearningActions()
  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const dificulties = useDifficultyFilter()

  // 있다면 제거, 없다면  추가
  const onClick = (v: IFilter) => {
    switch (v.category) {
      case 'job':
        setJobFilter(v)
        break
      case 'service':
        setServiceFilter(v)
        break
      case 'type':
        setTypeFilter(v)
        break
      case 'difficulty':
        setDifficultyFilter(v)
        break
    }
  }

  return (
    <div className={styles.container}>
      {[...jobs, ...services, ...types, ...dificulties].every(v => v.name !== item.name) ? (
        <FiPlus onClick={e => onClick(item)} />
      ) : (
        <FiMinus onClick={e => onClick(item)} />
      )}
      <span>{item.name}</span>
    </div>
  )
}

export default LearningFilterToggoleItem
