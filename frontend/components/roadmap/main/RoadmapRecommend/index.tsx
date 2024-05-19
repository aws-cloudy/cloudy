'use client'

import React, { useEffect, useState } from 'react'
import styles from './RoadmapRecommend.module.scss'
import RoadmapRecommendItem from '../RoadmapRecommendItem'
import { useSearchParams } from 'next/navigation'
import { MdInfoOutline } from 'react-icons/md'
import { useIsLogin } from '@/stores/authStore'
import { getRecommendRoadmaps } from '@/apis/recommend'
import RoadmapHazardSection from '../RoadmapHazardSection'
import { IRoadmapCard } from '@/types/roadmap'
import Loading from '@/components/common/Loading'

const RoadmapRecommend = () => {
  const [isFetching, setIsFetching] = useState<boolean>(false)
  const [list, setList] = useState<IRoadmapCard[]>([])
  const [isHazard, setIsHazard] = useState<boolean>(false)
  const params = useSearchParams()
  const keyword = params.get('query') || ''

  const isLogin = useIsLogin()

  const fetchRecommendRoadmaps = async () => {
    const learnings = await getRecommendRoadmaps(keyword)
    learnings === 'CE008' ? setIsHazard(true) : setList(learnings)
    setIsFetching(false)
  }

  useEffect(() => {
    setIsFetching(true)
    setIsHazard(false)
    keyword && isLogin && fetchRecommendRoadmaps()
  }, [params])

  if (!keyword) return
  if (!isLogin) return
  if (isHazard) return <RoadmapHazardSection />
  return (
    <article className={styles.container}>
      <div className={styles.title}>
        <div>존 Pick!</div>
        <div className={styles.infoWrap}>
          <MdInfoOutline color="#ff9900" cursor="pointer" />
          <p className={styles.arrowBox}>존은 로드맵을 추천해주는 클라우디의 서비스입니다</p>
        </div>
      </div>
      <div className={styles.desc}>{`'${keyword}'와 관련된 추천 로드맵입니다`}</div>
      {isFetching ? (
        <Loading />
      ) : (
        <div className={styles.wrap}>
          {list.map(item => (
            <RoadmapRecommendItem key={item.roadmapId} item={item} />
          ))}
        </div>
      )}
    </article>
  )
}

export default RoadmapRecommend
