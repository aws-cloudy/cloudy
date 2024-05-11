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
import { useRoadmapSearchValue } from '@/stores/roadmap'
import { getSession, useSession } from 'next-auth/react'
import { getBookmarks } from '@/apis/bookmark'

const RoadmapListSection = () => {
  const [list, setList] = useState<IRoadmapCard[]>([])
  const offset = useRef<number>(0)
  const hasMore = useRef<boolean>(true)
  const [isFetching, setIsFetching] = useState<boolean>(true)
  const [bookmarks, setBookmarks] = useState<{ roadmapId: number; bookmarkId: number }[]>([])

  const searchValue = useRoadmapSearchValue()

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

    const roadmaps = await getRoadmaps(offset.current, searchValue.keyword, searchValue.job, searchValue.service)

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
    if (isIntersecting && hasMore.current) {
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
  }, [searchValue])

  if (isFetching) return <Loading />
  if (list.length <= 0)
    return <Empty text="검색 결과가 없습니다. 필터를 다시 적용해보거나 올바른 검색어를 입력해주세요 !" />

  return (
    <>
      <div className={styles.container}>
        {list &&
          list.map(road => (
            <RoadmapCard
              item={{
                ...road,
                isScrapped: bookmarks.some(bm => bm.roadmapId === road.roadmapId),
                bookmarkId: bookmarks.find(bm => bm.roadmapId === road.roadmapId)?.bookmarkId ?? -1,
              }}
              key={road.roadmapId}
            />
          ))}
      </div>
      <Observer callback={observerCallback} />
    </>
  )
}

export default RoadmapListSection
