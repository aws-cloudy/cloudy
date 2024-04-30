import { IQuestionAnswer } from '@/types/community'
import DetailAnswerInput from '../DetailAnswerInput'
import styles from './DetailAnswer.module.scss'
import DetailAnswerITem from '../DetailAnswerITem'

function DetailAnswer({ answer }: { answer: IQuestionAnswer }) {
  const { id, answers, checkedId, authId, isWriter } = answer

  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <p className={styles.count}>댓글 {answers?.length}개</p>
        <div className={styles.answers}>
          {answers.length === 0 && <div className={styles.nodata}>등록된 댓글이 없습니다.</div>}
          {answers?.map(answer => (
            <DetailAnswerITem
              key={answer.id}
              ans={answer}
              isChecked={answer.id === checkedId}
              authId={authId}
              isWriter={isWriter}
            />
          ))}
        </div>
        <DetailAnswerInput id={id} authId={authId} />
      </div>
    </div>
  )
}

export default DetailAnswer
