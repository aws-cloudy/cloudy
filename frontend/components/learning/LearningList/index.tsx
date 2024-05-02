'use client'

import React, { useEffect, useRef, useState } from 'react'
import styles from './LearningList.module.scss'
import LearningCard from '@/components/common/LearningCard'
import { useLearninglayout } from '@/stores/search'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { ILearningCard, ILearningList } from '@/types/learning'
import { LEARNING_ROWS_PER_PAGE } from '@/constants/rows'
import { getLearnings } from '@/apis/learning'
import { useDifficultyFilter, useServiceFilter, useTypeFilter, usejobFilter } from '@/stores/learning'
import Observer from '@/components/common/Observer'
import Loading from '@/components/common/Loading'
import { getTextFilter } from '@/utils/getTextFilter'
import Empty from '@/components/common/Empty'

const LearningList = (props: ILearningList) => {
  const { keyword } = props

  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const difficulties = useDifficultyFilter()

  // 무한 스크롤
  const offset = useRef<number>(1)
  const hasMore = useRef<boolean>(true)
  const [list, setList] = useState<ILearningCard[]>([])
  const [isFetching, setIsFetching] = useState<boolean>(true)

  // fetch
  const fetchLearning = async () => {
    setIsFetching(true)
    if (!hasMore.current) return

    const apiLearnings = await getLearnings(
      offset.current,
      LEARNING_ROWS_PER_PAGE,
      keyword,
      getTextFilter(jobs),
      getTextFilter(services),
      getTextFilter(types),
      getTextFilter(difficulties),
    )

    if (apiLearnings) {
      apiLearnings.length < LEARNING_ROWS_PER_PAGE && (hasMore.current = false) // 더 이상 로드할 데이터가 없을 때
      setList(prev => [...prev, ...apiLearnings])
      offset.current += 1
    }

    setIsFetching(false)
  }

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isIntersecting && list.length > 0) {
      fetchLearning()
    }
  }

  useEffect(() => {
    window.scrollTo(0, 0) // 맨 위로 이동
    setList([])
    hasMore.current = true
    offset.current = 1
    fetchLearning()
  }, [keyword, jobs, services, types, difficulties])

  // 반응형 width 감지
  const { isTablet } = useResponsiveWidth()
  const [layout, setLayout] = useState<'grid' | 'justify'>('grid')
  const learningLayout = useLearninglayout()

  useEffect(() => {
    isTablet ? setLayout('justify') : setLayout(learningLayout)
  }, [isTablet, layout, learningLayout])

  if (isFetching) return <Loading />
  if (list.length <= 0)
    return <Empty text="검색 결과가 없습니다. 필터를 다시 적용해보거나 올바른 검색어를 입력해주세요 !" />
  return (
    <>
      <div className={layout === 'grid' ? styles.gridContainer : styles.justifyContainer}>
        {list && list.map(item => <LearningCard key={item.learningId} item={item} layout={layout} />)}
      </div>
      <Observer callback={observerCallback} />
    </>
  )
}

export default LearningList
