import React from 'react'
import styles from './ActivityCommentList.module.scss'
import { useRouter } from 'next/navigation'

const ActivityCommentList = ({ comments }: any) => {
  const router = useRouter()

  const formatTime = (createdAt: string) => {
    const date = new Date(createdAt)
    const year = date.getFullYear()
    const month = date.getMonth() + 1 < 10 ? `0${date.getMonth() + 1}` : date.getMonth() + 1
    const day = date.getDate() < 10 ? `0${date.getDate()}` : date.getDate()
    const hour = date.getHours() < 10 ? `0${date.getHours()}` : date.getHours()
    const minute = date.getMinutes() < 10 ? `0${date.getMinutes()}` : date.getMinutes()
    return `${year}.${month}.${day} ${hour}:${minute}`
  }

  const handleMove = (id: number) => {
    router.push(`/community/detail/${id}`)
  }

  return (
    <>
      {comments.map((comment: any) => (
        <section className={styles.section} key={comment.id} onClick={() => handleMove(comment.questionId)}>
          <div className={styles.tag}>{comment.type === 'community' ? '커뮤니티' : '로드맵'}</div>
          <div className={styles.desc}>
            {comment.desc.length > 60 ? `${comment.desc.substring(0, 60)}...` : comment.desc}
          </div>
          <div className={styles.row}>
            <div className={styles.gray}>{comment.memberName}</div>
            <div className={styles.gray}>|</div>
            <div className={styles.gray}>{formatTime(comment.createdAt)}</div>
          </div>
        </section>
      ))}
    </>
  )
}

export default ActivityCommentList
