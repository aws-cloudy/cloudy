import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './Favorites.module.scss'
import { roadmapData } from '@/components/roadmap/main/RoadmapListSection/roadmapData'
import { useEffect, useState } from 'react'
import { getBookmarks } from '@/apis/bookmark'


const Favorites = ({ user }: any) => {
  const [bookmarks, setBookmarks] = useState([])

  useEffect(() => {
    console.log('memberId: ', user.id)
    if (user.id) {
      getBookmarks(user.id)
        .then(setBookmarks)
        .catch(error => console.error('북마크 로드 실패', error))
    }
  }, [user.id])

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
