'use client'

import { IoPerson } from 'react-icons/io5'
import styles from './DetailContentSubtitle.module.scss'
import Avatar from '@/components/common/Avatar'
import { IDetailContentSubtitle } from '@/types/communityProps'
import { getFullDay } from '@/utils/common/getFullDay'
import axios from 'axios'
import { commuURL } from '@/apis/urls'
import { useRouter } from 'next/navigation'

function DetailContentSubtitle({ id, author, createdAt, authorId, authId }: IDetailContentSubtitle) {
  const date = new Date(createdAt)
  const router = useRouter()
  const isAuthor = authorId === authId

  const handleDelete = async () => {
    const res = await axios.delete(`${commuURL}/question/detail/delete`, { params: { id } })
    console.log(res)
    router.push('/community')
  }

  return (
    <div className={styles.container}>
      <div className={styles.user}>
        <Avatar Icon={IoPerson} size="small" />
        <p>{author}</p>
        <p className={styles.time}>{getFullDay(date)}</p>
      </div>
      <div className={styles.buttonBox}>
        {isAuthor && (
          <>
            <button>수정</button>
          </>
        )}
        <button onClick={() => handleDelete()}>삭제</button>
      </div>
    </div>
  )
}

export default DetailContentSubtitle
