import { IQuestionAnswer } from '@/types/community'
import DetailAnswerInput from '../DetailAnswerInput'
import styles from './DetailAnswer.module.scss'
import DetailAnswerITem from '../DetailAnswerITem'

function DetailAnswer({ answer }: { answer: IQuestionAnswer }) {
  const { id, answers, checkedId, authId, isWriter } = answer

  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <DetailAnswerInput id={id} authId={authId} />
        <p className={styles.count}>댓글 {answers?.length}개</p>
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
    </div>
  )
}

export default DetailAnswer
