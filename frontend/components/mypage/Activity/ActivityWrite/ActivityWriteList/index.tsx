import { Key } from 'react'
import styles from './ActivityWriteList.module.scss'

const ActivityWriteList = ({ posts }: any) => {
  return (
    <>
      {posts.map((post: any) => (
        <section className={styles.section} key={post.id}>
          <div className={styles.row}>
            <div className={styles.title}>{post.title}</div>
            <div className={post.status === '미해결' ? styles.unResolve : styles.resolve}>{post.status}</div>
          </div>
          <div className={styles.context}>
            {post.context.length > 100 ? `${post.context.substring(0, 100)}...` : post.context}
          </div>
          <div className={styles.row}>
            {post.tags.map((tag: any) => (
              <div className={styles.tag} key={tag}>
                #{tag}
              </div>
            ))}
          </div>
        </section>
      ))}
    </>
  )
}

export default ActivityWriteList
