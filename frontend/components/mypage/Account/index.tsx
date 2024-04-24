import styles from './Account.module.scss'

const Account = ({ user }: any) => {
  return (
    <section className={styles.section}>
      <div className={styles.intro}>기본 정보</div>
      <div className={styles.row}>
        <div className={styles.title}>이름</div>
        <div className={styles.block}>{user.name}</div>
      </div>
      <div className={styles.row}>
        <div className={styles.title}>이메일</div>
        <div className={styles.block}>{user.email}</div>
      </div>
      <div className={styles.row}>
        <div className={styles.title}>직무</div>
        <div className={styles.block}>{user.email}</div>
      </div>
      <div className={styles.row}>
        <div className={styles.title}>관심 서비스</div>
        <div className={styles.block}>{user.email}</div>
        <div className={styles.switchButton}>변경</div>
      </div>
    </section>
  )
}

export default Account
