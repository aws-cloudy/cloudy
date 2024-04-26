import styles from './ActivityWrite.module.scss'
import ActivityWriteList from './ActivityWriteList'

const ActivityWrite = ({ posts }: any) => {
  return (
    <section className={styles.section}>
      <div>총 {posts.length}건의 글이 있습니다.</div>
      <ActivityWriteList posts={posts} />
    </section>
  )
}
export default ActivityWrite
