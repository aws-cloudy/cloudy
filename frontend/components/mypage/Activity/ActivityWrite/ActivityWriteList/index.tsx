import { Key } from 'react'
import styles from './ActivityWriteList.module.scss'

const ActivityWriteList = ({ posts }: any) => {
  return (
    <>
      {posts.map((post: any) => (
        <section className={styles.section} key={post.id}>
          <div className={styles.row}>
            <div className={styles.title}>{post.title}</div>
            <div className={post.checkedId ? styles.resolve : styles.unResolve}>
              {post.checkedId ? '해결' : '미해결'}
            </div>
          </div>
          <div className={styles.desc}>{post.desc.length > 100 ? `${post.desc.substring(0, 100)}...` : post.desc}</div>
          <div className={styles.row}>
            {post.hashtags.map((hashtag: any) => (
              <div className={styles.tag} key={hashtag.hashtag.id}>
                #{hashtag.hashtag.title}
              </div>
            ))}
          </div>
        </section>
      ))}
    </>
  )
}

export default ActivityWriteList
