'use client'

import React, { useEffect, useRef, useState } from 'react'
import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './RoadmapListSection.module.scss'
import { roadmapData } from './roadmapData'
import { getRoadmaps } from '@/apis/roadmap'
import { IRoadmapCard } from '@/types/roadmap'
import Observer from '@/components/common/Observer'
import Loading from '@/components/common/Loading'
import Empty from '@/components/common/Empty'
import { ROADMAP_ROWS_PER_PAGE } from '@/constants/rows'

const RoadmapListSection = () => {
  const [list, setList] = useState<IRoadmapCard[]>([])
  const offset = useRef<number>(0)
  const hasMore = useRef<boolean>(true)
  const [isFetching, setIsFetching] = useState<boolean>(true)

  // fetch 로드맵
  const fetchRoadmaps = async () => {
    setIsFetching(true)
    if (!hasMore.current) return

    const roadmaps = await getRoadmaps(offset.current)

    if (roadmaps) {
      roadmaps.length < ROADMAP_ROWS_PER_PAGE && (hasMore.current = false)
      setList(prev => [...prev, ...roadmaps])
      offset.current += 1
    }
    setIsFetching(false)
  }

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isIntersecting && hasMore.current) {
      fetchRoadmaps()
    }
  }

  useEffect(() => {
    window.scrollTo(0, 0)
    setList([])
    hasMore.current = true
    offset.current = 0
    fetchRoadmaps()
  }, [])

  if (isFetching) return <Loading />
  if (list.length <= 0)
    return <Empty text="검색 결과가 없습니다. 필터를 다시 적용해보거나 올바른 검색어를 입력해주세요 !" />
  return (
    <>
      <div className={styles.container}>
        {list && list.map(road => <RoadmapCard item={road} key={road.roadmapId} />)}
      </div>
      <Observer callback={observerCallback} />
    </>
  )
}

export default RoadmapListSection
