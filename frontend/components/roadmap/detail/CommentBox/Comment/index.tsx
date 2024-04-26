import styles from './Comment.module.scss'
import React from 'react'

const Comment = () => {
  return (
    <div>
      <div className={styles.name}>김싸피</div>
      <div className={styles.date}>2024.04.15 11:53</div>
      <div className={styles.comment}>이거대로 공부하고 자격증 합격했어요. 강추합니다 !!</div>
    </div>
  )
}

export default Comment
