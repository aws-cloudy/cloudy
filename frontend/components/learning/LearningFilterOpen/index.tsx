import React from 'react'
import { ILearningFilterOpen } from '@/types/learning'
import styles from './LearningFilterOpen.module.scss'
import { MdOutlineClose } from 'react-icons/md'

const LearningFilterOpen = (props: ILearningFilterOpen) => {
  const { closeFilter } = props

  return (
    <div className={styles.container}>
      <div onClick={e => closeFilter()} className={styles.closeWrap}>
        <span>close filter</span>
        <MdOutlineClose />
      </div>
      <div>filter 열렸을 때</div>
    </div>
  )
}

export default LearningFilterOpen
