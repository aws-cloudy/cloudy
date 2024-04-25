import React from 'react'
import { ILearningFilterOpen } from '@/types/learning'
import styles from './LearningFilterOpen.module.scss'
import { MdOutlineClose } from 'react-icons/md'
import LearningTagList from '../LearningTagList'
import LearningFilterToggle from '../LearningFilterToggle'
import { difficultyData, jobData, serviceData, typeData } from '@/constants/learning'

const LearningFilterOpen = (props: ILearningFilterOpen) => {
  const { closeFilter } = props

  return (
    <div className={styles.container}>
      <div onClick={e => closeFilter()} className={styles.closeWrap}>
        <span>close filter</span>
        <MdOutlineClose />
      </div>
      <div className={styles.filterWrap}>
        <LearningFilterToggle title="직무" data={jobData} />
        <LearningFilterToggle title="주요 서비스" data={serviceData} />
        <LearningFilterToggle title="분류" data={typeData} />
        <LearningFilterToggle title="난이도" data={difficultyData} />
        <LearningTagList />
      </div>
    </div>
  )
}

export default LearningFilterOpen
