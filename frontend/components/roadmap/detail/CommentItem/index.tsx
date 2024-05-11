import { IComment } from '@/types/roadmap'
import styles from './CommentItem.module.scss'
import React from 'react'
import { getFullDay } from '@/utils/common/getFullDay'
import { deleteRoadmapComment } from '@/apis/comment'

const CommentItem = ({
  roadmapId,
  comments,
  memberId,
}: {
  roadmapId: number
  comments: IComment[]
  memberId: string | undefined
}) => {
  console.log(comments)
  const handleDelete = async (commentId: number) => {
    try {
      await deleteRoadmapComment(roadmapId, commentId)
    } catch (e) {
      console.log(e)
    }
  }

  return (
    <>
      {comments.map((comment: IComment) => {
        const date = new Date(comment.regAt)
        const fulldate = getFullDay(date)
        const isWriter = comment.member.id === memberId

        return (
          <div key={comment.commentId}>
            <div className={styles.row}>
              <div className={styles.name}>{comment.member.name}</div>
              <div className={styles.date}>{fulldate}</div>
              {isWriter && <button onClick={() => handleDelete(comment.commentId)}>삭제</button>}
            </div>
            <div className={styles.comment}>{comment.content}</div>
          </div>
        )
      })}
    </>
  )
}

export default CommentItem
