import MainSearch from '../MainSearch'
import styles from './MainHeader.module.scss'

function MainHeader() {
  return (
    <section className={styles.container}>
      <div className={styles.searchBox}>
        <h1 className={styles.title}>당신의 강의를 검색해 보세요</h1>
        <MainSearch />
      </div>
      <video className={styles.video} src="/img/mainBG.mp4" autoPlay loop muted />
    </section>
  )
}

export default MainHeader
