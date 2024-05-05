import LearningCard from '@/components/common/LearningCard'
import { ILearningCard } from '@/types/learning'
import React from 'react'
import styles from './DetailCourse.module.scss'

const DetailCourse = ({ data }: { data: ILearningCard[] }) => {
  return (
    <div className={styles.container}>
      {data.map(item => (
        <div className={styles.wrap}>
          <div className={styles.circle} />
          <div className={styles.line} />
          <LearningCard layout="justify" item={item} />
        </div>
      ))}
    </div>
  )
}

export default DetailCourse
