import styles from './ActivityWriteList.module.scss'

const ActivityWriteList = () => {
  return (
    <section className={styles.section}>
      <div className={styles.row}>
        <div>이거 왜 오류난건가요?</div>
        <div className={styles.status}>미해결</div>
      </div>
      <div className={styles.context}>
        강의 내용대로 하고 있었는데 아래와 같은 오류가 발생했습니다. 설정이 잘못된 걸까요? 질문 내용 질문 내용 질문 내용
        질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용 질문 내용
        질문 내용 질문 내용 질문 ...
      </div>
      <div className={styles.row}>
        <div className={styles.tag}>#S3</div>
        <div className={styles.tag}>#Bedrock</div>
      </div>
    </section>
  )
}

export default ActivityWriteList
