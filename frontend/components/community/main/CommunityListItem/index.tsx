import React from 'react'
import styles from './CommunityListItem.module.scss'
import CommunityListItemBadge from '../CommunityListItemBadge'
import { ICommunityListItem } from '@/types/community'
import { FiMessageCircle } from 'react-icons/fi'

function CommunityListItem({ question }: { question: ICommunityListItem }) {
  const desc = question.desc.length > 120 ? question.desc.substring(0, 120) + '...' : question.desc

  return (
    <div className={styles.container}>
      <div className={styles.titleBox}>
        <div className={styles.titleBoxInner}>
          <h2 className={styles.title}>{question.title}</h2>
          <CommunityListItemBadge checked={Boolean(question.checkedId)} />
        </div>
        <div className={styles.answers}>
          <FiMessageCircle />
          <p>{question.answers}</p>
        </div>
      </div>
      <p className={styles.desc}>{desc}</p>
      <div className={styles.footer}>
        <div className={styles.hash}>
          {question.hashtags.map((h, i) => (
            <span key={i} className={styles.hashtag}>
              #{h}
            </span>
          ))}
        </div>
        <p className={styles.answers}>{question.createdAt}</p>
      </div>
    </div>
  )
}

export default React.memo(CommunityListItem)
