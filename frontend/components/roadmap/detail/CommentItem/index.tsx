import styles from './CommentItem.module.scss'
import React from 'react'

const formatDate = (isoString: string | number | Date) => {
  const date = new Date(isoString);
  const datePart = date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour12: false
  }).replace(/(\d+)\. (\d+)\. (\d+)/, '$1.$2.$3');

  const timePart = date.toLocaleTimeString('ko-KR', {
    hour: '2-digit',
    minute: '2-digit',
    hour12: false
  });

  return `${datePart} ${timePart}`;
};


const CommentItem = ({ comments }: any) => {
  return (
    <>
      {comments.map((comment: any) => (
        <div key={comment.commentId}>
          <div className={styles.row}>
            <div className={styles.name}>{comment.member.name}</div>
            <div className={styles.date}>{formatDate(comment.regAt)}</div>
          </div>
          <div className={styles.comment}>{comment.content}</div>
        </div>
      ))}
    </>
  )
}

export default CommentItem
