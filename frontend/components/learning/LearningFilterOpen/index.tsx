import React from 'react'
import { ILearningFilterOpen } from '@/types/learning'

const LearningFilterOpen = (props: ILearningFilterOpen) => {
  const { closeFilter } = props

  return (
    <div>
      <div onClick={e => closeFilter()}>close filter</div>
      <div>filter 열렸을 때</div>
    </div>
  )
}

export default LearningFilterOpen
