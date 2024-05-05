import styles from './CommentItem.module.scss'
import React from 'react'

const CommentItem = ({ comments }: any) => {
  return (
    <>
      {comments.map((comment: any) => (
        <div key={comment.id}>
          <div className={styles.row}>
            <div className={styles.name}>{comment.name}</div>
            <div className={styles.date}>{comment.date}</div>
          </div>
          <div className={styles.comment}>{comment.context}</div>
        </div>
      ))}
    </>
  )
}

export default CommentItem
