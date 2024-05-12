import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './Favorites.module.scss'
import { useEffect, useState } from 'react'
import { getBookmarks } from '@/apis/bookmark'
import { IRoadmapCard } from '@/types/roadmap'

const Favorites = ({ user }: any) => {
  const [bookmarks, setBookmarks] = useState<IRoadmapCard[]>([])

  useEffect(() => {
    console.log('memberId: ', user.id)
    if (user.id) {
      getBookmarks(user.id)
        .then(data => setBookmarks(data.roadmaps))
        .catch(error => console.error('북마크 로드 실패', error))
    }
    
  }, [user.id])

  return (
    <section className={styles.section}>
      <div className={styles.intro}>찜한 로드맵</div>
      <div className={styles.cardContainer}>
        {bookmarks.map(road => (
          <RoadmapCard item={{...road, isScrapped:true, isUseMypage:true}} key={road.roadmapId}/>
        ))}
      </div>
    </section>
  )
}

export default Favorites
