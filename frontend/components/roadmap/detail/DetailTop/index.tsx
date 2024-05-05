import React from 'react'
import styles from './DetailTop.module.scss'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import Avatar from '@/components/common/Avatar'

const DetailTop = () => {
  return (
    <>
      <div className={styles.tags}>
        <div>#DataWrangler</div>
        <div>#DataWrangler</div>
      </div>
      <div className={styles.title}>
        Analyze security findings faster with no-code data preparation using generative AI and Amazon SageMaker Canvas
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
