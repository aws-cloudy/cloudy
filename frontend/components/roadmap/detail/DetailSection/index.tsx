import styles from './DetailSection.module.scss'
import React from 'react'
import CommentSection from '../CommentSection'
import DetailTop from '../DetailTop'
import DetailTab from '../DetailTab'
import { IRoadmapDetailSection } from '@/types/roadmap'

const DetailSection = ({ data }: IRoadmapDetailSection) => {
  return (
    <section className={styles.section}>
      <DetailTop data={data.detail} />
      <div className={styles.row}>
        <div className={styles.left}>
          <DetailTab />
        </div>
        <div className={styles.right}>
          <CommentSection />
        </div>
      </div>
    </section>
  )
}

export default DetailSection
