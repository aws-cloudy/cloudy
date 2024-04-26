import Comment from './Comment'
import styles from './Commnent.module.scss'
import React from 'react'

const CommentBox = () => {
  return (
    <>
      <div className={styles.counts}>댓글 2개</div>
      <input type="text" placeholder="댓글 달기..." className={styles.inputBox} />
      <div className={styles.notice}>첫번째 댓글을 남겨보세요.</div>
      <Comment />
    </>
  )
}

export default CommentBox
