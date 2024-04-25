import styles from './ActivityComment.module.scss'
import ActivityCommentList from './ActivityCommentList'

const ActivityComment = ({ comments }: any) => {
  return (
    <section className={styles.section}>
      <div>총 {comments.length}건의 댓글이 있습니다.</div>
      <ActivityCommentList comments={comments} />
    </section>
  )
}
export default ActivityComment
