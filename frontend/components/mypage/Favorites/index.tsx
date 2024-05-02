import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './Favorites.module.scss'
import { roadmapData } from '@/components/roadmap/main/RoadmapListSection/roadmapData'

const Favorites = () => {
  return (
    <section className={styles.section}>
      <div className={styles.intro}>찜한 로드맵</div>
      <div className={styles.cardContainer}>
        {roadmapData.map(road => (
          <RoadmapCard item={road} key={road.roadmapId} />
        ))}
      </div>
    </section>
  )
}

export default Favorites
