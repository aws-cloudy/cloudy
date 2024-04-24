import React from 'react'
import { ILearningFilterClose } from '@/types/learning'

const LearningFilterClose = (props: ILearningFilterClose) => {
  const { openFilter } = props

  return (
    <div>
      <div onClick={e => openFilter()}>filter open</div>
      <div>filter 닫혔을 때</div>
    </div>
  )
}

export default LearningFilterClose
