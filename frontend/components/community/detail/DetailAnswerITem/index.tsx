import { IDetailAnswerItem } from '@/types/communityProps'
import styles from './DetailAnswerITem.module.scss'
import { getFullDay } from '@/utils/common/getFullDay'

function DetailAnswerITem({ ans, isChecked }: IDetailAnswerItem) {
  const date = new Date(ans.createdAt)

  return (
    <div className={styles.container}>
      <div className={styles.titleBox}>
        <div className={styles.nameBox}>
          <p className={styles.name}>{ans.memberName}</p>
          <p className={styles.date}>{getFullDay(date)}</p>
          {isChecked && <div className={styles.badge}>질문자 채택</div>}
        </div>
        <div className={styles.buttonBox}>
          <button>삭제</button>
        </div>
      </div>
      {ans.desc}
    </div>
  )
}

export default DetailAnswerITem
