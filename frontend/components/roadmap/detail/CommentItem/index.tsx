import styles from './CommentItem.module.scss'
import React from 'react'

const CommentItem = ({ comments }: any) => {
  return (
    <>
      {comments.map((comment: any) => (
        <div key={comment.commentId}>
          <div className={styles.row}>
            <div className={styles.name}>{comment.memeber.name}</div>
            <div className={styles.date}>{comment.regAt}</div>
          </div>
          <div className={styles.comment}>{comment.content}</div>
        </div>
      ))}
    </>
  )
}

export default CommentItem
