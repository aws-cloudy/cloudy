import { IQuestionAnswer } from '@/types/community'
import DetailAnswerInput from '../DetailAnswerInput'
import styles from './DetailAnswer.module.scss'
import DetailAnswerITem from '../DetailAnswerITem'

function DetailAnswer({ answer }: { answer: IQuestionAnswer }) {
  const { id, answers, checkedId } = answer

  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <p className={styles.count}>댓글 {answers?.length}개</p>
        <DetailAnswerInput id={id} />
        {answers?.map(answer => <DetailAnswerITem key={answer.id} ans={answer} isChecked={answer.id === checkedId} />)}
      </div>
    </div>
  )
}

export default DetailAnswer
