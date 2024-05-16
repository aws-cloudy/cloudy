import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './Favorites.module.scss'
import { useState } from 'react'
import { IRoadmapCard } from '@/types/roadmap'
const Favorites = ({ bookmarksData, onBookmarkDelete }: any) => {
  const [bookmarks, setBookmarks] = useState<IRoadmapCard[]>(bookmarksData)

  return (
    <section className={styles.section}>
      <div className={styles.intro} id="test_id">
        찜한 로드맵
      </div>
      {bookmarks.length === 0 ? (
        <div>찜한 로드맵이 없습니다. 마음에 드는 로드맵을 찾아보세요!</div>
      ) : (
        <div className={styles.cardContainer}>
          {bookmarks.map(road => (
            <RoadmapCard
              item={{ ...road, isScrapped: true, isUseMypage: true }}
              key={road.roadmapId}
              onBookmarkDelete={onBookmarkDelete}
            />
          ))}
        </div>
      )}
    </section>
  )
}

export default Favorites
