'use client'

import { IDetailAnswerItem } from '@/types/communityProps'
import styles from './DetailAnswerITem.module.scss'
import axios from 'axios'
import { commuURL } from '@/apis/urls'
import { useRouter } from 'next/navigation'

function DetailAnswerITem({ ans, isChecked, authId, isWriter }: IDetailAnswerItem) {
  const isAuthor = ans.memberId === authId
  const router = useRouter()

  const deleteAnswer = async () => {
    await axios.delete(`${commuURL}question/answer/delete`, { params: { id: ans.id } })
    router.refresh()
  }

  const checkAnswer = async () => {
    const ansId = isChecked ? null : ans.id
    await axios.put(`${commuURL}question/answer/check`, { postId: ans.questionId, ansId })
    router.refresh()
  }

  return (
    <div className={styles.container}>
      <div className={styles.titleBox}>
        <div className={styles.nameBox}>
          <p className={styles.name}>{ans.memberName}</p>
          {isChecked && <div className={styles.badge}>질문자 채택</div>}
        </div>

        <div className={styles.buttonBox}>
          {isWriter && <button onClick={() => checkAnswer()}>{isChecked ? '채택해제' : '채택하기'}</button>}
          {isAuthor && <button onClick={() => deleteAnswer()}>삭제</button>}
        </div>
      </div>
      {ans.desc}
    </div>
  )
}

export default DetailAnswerITem
