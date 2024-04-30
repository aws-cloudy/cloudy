import React from 'react'
import styles from './CommunityListItem.module.scss'
import CommunityListItemBadge from '../CommunityListItemBadge'
import { ICommunityListItem } from '@/types/community'
import { FiMessageCircle } from 'react-icons/fi'
import { useRouter } from 'next/navigation'

function CommunityListItem({ question }: { question: ICommunityListItem }) {
  const descWithoutImage = question.desc.replace(/!\[alt text]\(.+\)/g, '')
  let desc = descWithoutImage.length > 120 ? descWithoutImage.substring(0, 120) + '...' : descWithoutImage
  const router = useRouter()
  const handleClick = () => {
    router.push(`/community/detail/${question.id}`)
  }

  return (
    <div className={styles.container} onClick={() => handleClick()}>
      <div className={styles.titleBox}>
        <div className={styles.titleBoxInner}>
          <h2 className={styles.title}>{question.title}</h2>
          <CommunityListItemBadge checked={Boolean(question.checkedId)} />
        </div>
        <div className={styles.answers}>
          <FiMessageCircle />
          <p>{question._count.answers}</p>
        </div>
      </div>
      <p className={styles.desc}>{desc}</p>
      <div className={styles.footer}>
        <div className={styles.hash}>
          {question.hashtags.map(h => (
            <span key={h.hashtag.id} className={styles.hashtag}>
              #{h.hashtag.title}
            </span>
          ))}
        </div>
        <p className={styles.answers}>{new Date(question.createdAt).toDateString()}</p>
      </div>
    </div>
  )
}

export default React.memo(CommunityListItem)
