import React from 'react'
import { useForm } from 'react-hook-form'
import styles from './CommentSectionForm.module.scss'
import { FaSquareCheck } from 'react-icons/fa6'
import { postRoadmapComment } from '@/apis/comment'

function CommentSectionForm({ roadmapId, memberId }: { roadmapId: number; memberId: string | undefined }) {
  const { register, handleSubmit, getValues, reset } = useForm<{ comment: string }>()
  const onSubmit = async () => {
    const comment = getValues('comment')
    if (!memberId) return

    try {
      const res = await postRoadmapComment(roadmapId, memberId, comment)
      console.log(res)
      reset()
    } catch (e) {
      console.log(e)
    }
  }

  return (
    <form className={styles.container} onSubmit={handleSubmit(onSubmit)}>
      <textarea
        id="comment-input"
        placeholder="댓글 달기..."
        className={styles.inputBox}
        {...register('comment')}
        required
        defaultValue={''}
        autoComplete="off"
      ></textarea>
      <button type="submit" className={styles.iconBox}>
        <FaSquareCheck className={styles.icon} />
      </button>
    </form>
  )
}

export default CommentSectionForm
