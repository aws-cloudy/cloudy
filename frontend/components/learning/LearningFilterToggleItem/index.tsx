import React from 'react'
import styles from './LearningFilterToggleItem.module.scss'
import { FiPlus } from 'react-icons/fi'
import { FiMinus } from 'react-icons/fi'
import { IFilter, ILearningFilterToggoleItem } from '@/types/learning'
import { useDifficultyFilter, useServiceFilter, useTypeFilter, usejobFilter } from '@/stores/learning'
import { useSetFilter } from '@/hooks/useSetFilter'
import { useRouter, useSearchParams } from 'next/navigation'

const LearningFilterToggoleItem = (props: ILearningFilterToggoleItem) => {
  const { item, list, setList } = props

  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const dificulties = useDifficultyFilter()

  const params = useSearchParams()

  //const setFilter = useSetFilter(item)

  const onClick = () => {
    const index = list.indexOf(item)
    index === -1 ? setList([...list, item]) : setList(list.filter(v => v != item))
    // const index = state.jobs.indexOf(v)
    // return index === -1
    //   ? { ...state, jobs: [...state.jobs, v], filterCount: (state.filterCount += 1) }
    //   : { ...state, jobs: state.jobs.filter((_, i) => i !== index), filterCount: (state.filterCount -= 1) }
    //setFilter()
    // setList([...list, item])
    // console.log('현재 필터', params.get('type'))
    // console.log(item)
    // params.forEach(v => console.log(v))
  }

  return (
    <div className={styles.container} onClick={e => onClick()}>
      <div className={styles.wrap}>
        {list.every(v => v.name !== item.name) ? (
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
