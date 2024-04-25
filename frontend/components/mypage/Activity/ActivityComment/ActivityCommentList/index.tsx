import React from 'react'
import styles from './ActivityCommentList.module.scss'

const ActivityCommentList = ({ comments }: any) => {
  return (
    <>
      {comments.map((comment: any) => (
        <section className={styles.section} key={comment.id}>
          <div className={styles.tag}>{comment.category}</div>
          <div className={styles.context}>
            {comment.context.length > 60 ? `${comment.context.substring(0, 60)}...` : comment.context}
          </div>
          <div className={styles.row}>
            <div className={styles.gray}>{comment.writer}</div>
            <div className={styles.gray}>|</div>
            <div className={styles.gray}>{comment.date}</div>
          </div>
        </section>
      ))}
    </>
  )
}

export default ActivityCommentList
