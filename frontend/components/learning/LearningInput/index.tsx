import React from 'react'
import styles from './LearningInput.module.scss'
import { ILearningInput } from '@/types/learning'

const LearningInput = (props: ILearningInput) => {
  const { value, setValue } = props

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => setValue(e.target.value)

  return (
    <>
      <input className={styles.input} placeholder="무엇이든 검색해보세요 ..." value={value} onChange={onChange} />
    </>
  )
}

export default LearningInput
