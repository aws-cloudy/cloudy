import React from 'react'
import styles from './LearningTagList.module.scss'
import LearningTagListItem from '../LearningTagListItem'

const LearningTagList = () => {
  return (
    <div className={styles.container}>
      <LearningTagListItem text="안녕하세용" />
      <LearningTagListItem text="태그 테스트 메롱" />
      <LearningTagListItem text="안녕하세요" />
      <LearningTagListItem text="초급" />
      <LearningTagListItem text="고급" />
    </div>
  )
}

export default LearningTagList
