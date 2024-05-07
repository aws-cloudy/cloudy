import React from 'react'
import styles from './DetailTop.module.scss'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import Avatar from '@/components/common/Avatar'
import { IRoadmapCard } from '@/types/roadmap'

const DetailTop = ({ data }: { data: IRoadmapCard }) => {
  return (
    <>
      <div className={styles.tags}>
        <div>#{data.job}</div>
        <div>#{data.service}</div>
      </div>
      <div className={styles.title}>
        {data.title}
        <BsBookmarkFill className={styles.bookmark} />
      </div>
      <div className={styles.profile}>
        <div className={styles.profileImage}>
          <Avatar Icon={BsChat} size="small" />
        </div>
        <div className={styles.name}>클라우디</div>
      </div>
    </>
  )
}

export default DetailTop
