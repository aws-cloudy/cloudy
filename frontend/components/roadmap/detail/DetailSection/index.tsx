import styles from './DetailSection.module.scss'
import React from 'react'
import CommentSection from '../CommentSection'
import DetailTop from '../DetailTop'
import DetailTab from '../DetailTab'
import { IRoadmapDetailData } from '@/types/roadmap'

const DetailSection = ({ data, comments, memberId, bookmarkId }: IRoadmapDetailData) => {
  return (
    <section className={styles.section}>
      <DetailTop data={data.detail} bookId={bookmarkId ? bookmarkId : null} />
      <div className={styles.row}>
        <div className={styles.left}>
          <DetailTab data={data} memberId={memberId} comments={comments} />
        </div>
        <div className={styles.right}>
          <CommentSection data={data} memberId={memberId} comments={comments} />
        </div>
      </div>
    </section>
  )
}

export default DetailSection
