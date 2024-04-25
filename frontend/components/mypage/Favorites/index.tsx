import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './Favorites.module.scss'

const Favorites = () => {
  return (
    <section className={styles.section}>
      <div className={styles.intro}>찜한 로드맵</div>
      <RoadmapCard />
    </section>
  )
}

export default Favorites
