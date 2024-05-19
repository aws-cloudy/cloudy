'use client'

import React, { useEffect, useRef, useState } from 'react'
import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './RoadmapListSection.module.scss'
import { getRoadmaps } from '@/apis/roadmap'
import { IRoadmapCard } from '@/types/roadmap'
import Observer from '@/components/common/Observer'
import Loading from '@/components/common/Loading'
import Empty from '@/components/common/Empty'
import { ROADMAP_ROWS_PER_PAGE } from '@/constants/rows'
import { getSession, useSession } from 'next-auth/react'
import { getBookmarks } from '@/apis/bookmark'
import { useSearchParams } from 'next/navigation'

const RoadmapListSection = () => {
  const [list, setList] = useState<IRoadmapCard[]>([])
  const offset = useRef<number>(0)
  const hasMore = useRef<boolean>(true)
  const [isFetching, setIsFetching] = useState<boolean>(true)
  const [bookmarks, setBookmarks] = useState<{ roadmapId: number; bookmarkId: number }[]>([])

  const params = useSearchParams()
  const keyword = params.get('query') || ''

  // 찜한 로드맵 목록
  const fetchBookmarks = async () => {
    const session = await getSession()
    const memberId = session?.user.id

    if (!memberId) return
    const response = await getBookmarks(memberId)
    console.log(response)
    if (response?.roadmaps) {
      const marksData = response.roadmaps.map((bookmark: Bookmark) => ({
        roadmapId: bookmark.roadmapId,
        bookmarkId: bookmark.bookmarkId,
      }))
      setBookmarks(marksData)
    }
  }

  // fetch 로드맵
  const fetchRoadmaps = async () => {
    if (!hasMore.current) return

    const job = params.get('job') || ''
    const service = params.get('service') || ''

    const roadmaps = await getRoadmaps(offset.current, keyword, job, service)

    console.log(roadmaps)

    if (roadmaps) {
      roadmaps.length < ROADMAP_ROWS_PER_PAGE && (hasMore.current = false)
      const updatedRoadmaps = roadmaps.map((roadmap: IRoadmapCard) => {
        const bookmark = bookmarks.find(bm => bm.roadmapId === roadmap.roadmapId)
        return {
          ...roadmap,
          isScrapped: !bookmark,
          bookmarkId: bookmark ? bookmark.bookmarkId : -1,
        }
      })
      setList(prev => [...prev, ...updatedRoadmaps])
      offset.current += 1
    }
    setIsFetching(false)
    return
  }

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isIntersecting && hasMore.current && offset.current > 0) {
      fetchRoadmaps()
    }
  }

  useEffect(() => {
    setIsFetching(true)
    window.scrollTo(0, 0)
    setList([])
    hasMore.current = true
    offset.current = 0
    fetchBookmarks().then(fetchRoadmaps)
  }, [params])

  if (isFetching) return <Loading />

  return (
    <div className={styles.wrap}>
      <div className={styles.title}>전체 검색</div>
      {keyword && <div className={styles.desc}>{`'${keyword}' 검색 결과입니다`}</div>}
      {list.length <= 0 ? (
        <div className={styles.emptyWrap}>
          <Empty text="검색 결과가 없습니다. 필터를 다시 적용해보거나 올바른 검색어를 입력해주세요 !" />
        </div>
      ) : (
        <div className={styles.container}>
          {list.map(road => (
            <RoadmapCard
              item={{
                ...road,
                isScrapped: bookmarks.some(bm => bm.roadmapId === road.roadmapId),
                bookmarkId: bookmarks.find(bm => bm.roadmapId === road.roadmapId)?.bookmarkId ?? -1,
                isUseMypage: false,
              }}
              key={road.roadmapId}
              onBookmarkDelete={function (bookmarkId: number): void {}}
            />
          ))}
        </div>
      )}

      <Observer callback={observerCallback} />
    </div>
  )
}

export default RoadmapListSection
