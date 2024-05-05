import styles from './DetailSection.module.scss'
import React from 'react'
import CommentSection from '../CommentSection'
import DetailTop from '../DetailTop'
import DetailTab from '../DetailTab'

const DetailSection = () => {
  return (
    <section className={styles.section}>
      <DetailTop />
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
