'use client'

import { IoPerson } from 'react-icons/io5'
import styles from './DetailContentSubtitle.module.scss'
import Avatar from '@/components/common/Avatar'
import { IDetailContentSubtitle } from '@/types/communityProps'
import { getFullDay } from '@/utils/common/getFullDay'

function DetailContentSubtitle({ id, user, createdAt }: IDetailContentSubtitle) {
  const date = new Date(createdAt)

  return (
    <div className={styles.container}>
      <div className={styles.user}>
        <Avatar Icon={IoPerson} size="small" />
        <p>{user}</p>
        <p className={styles.time}>{getFullDay(date)}</p>
      </div>
      <div className={styles.buttonBox}>
        <button>수정</button>
        <button>삭제</button>
      </div>
    </div>
  )
}

export default DetailContentSubtitle
