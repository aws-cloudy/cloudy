import styles from './ActivityWrite.module.scss'
import ActivityWriteList from './ActivityWriteList'

const ActivityWrite = () => {
  return (
    <section className={styles.section}>
      <div>총 0건의 글이 있습니다.</div>
      <ActivityWriteList />
    </section>
  )
}
export default ActivityWrite
