'use client'

import styles from './CommentSection.module.scss'
import CommentItem from '../CommentItem'
import CommentSectionForm from '../CommentSectionForm'
import { IRoadmapDetailData } from '@/types/roadmap'

const CommentSection = ({ data, comments, memberId }: IRoadmapDetailData) => {
  const { detail } = data

  return (
    <div className={styles.section}>
      <div className={styles.counts}>댓글 {comments.length}개</div>
      <CommentSectionForm roadmapId={detail.roadmapId} memberId={memberId} />
      {comments.length > 0 ? (
        <CommentItem roadmapId={detail.roadmapId} memberId={memberId} comments={comments} />
      ) : (
        <div className={styles.notice}>첫번째 댓글을 남겨보세요.</div>
      )}
    </div>
  )
}

export default CommentSection
