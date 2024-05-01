'use client'

import React, { useCallback, useEffect, useState } from 'react'
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

const LearningList = (props: ILearningList) => {
  const { keyword } = props

  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const difficulties = useDifficultyFilter()

  // 무한 스크롤
  const [offset, setOffset] = useState<number>(2)
  const [list, setList] = useState<ILearningCard[]>([])
  const [hasMore, setHasMore] = useState<boolean>(true)
  const [isFetching, setIsFetching] = useState<boolean>(true)

  const loadMoreLearning = useCallback(async () => {
    if (!hasMore) return

    const job = ''
    const service = ''
    const type = ''
    const difficulty = ''

    setIsFetching(false)
    const apiLearnings = await getLearnings(offset, LEARNING_ROWS_PER_PAGE, keyword, job, service, type, difficulty)

    if (apiLearnings.length < LEARNING_ROWS_PER_PAGE) {
      setHasMore(false) // 더 이상 로드할 데이터가 없을 때
    }

    setList([...list, ...apiLearnings])
    setOffset(offset + 1)
    setIsFetching(false)
  }, [offset, hasMore, jobs, services, types, difficulties])

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isIntersecting) {
      loadMoreLearning()
    }
  }

  useEffect(() => {
    setList([])
    setHasMore(true)
    setOffset(1)
    loadMoreLearning()
  }, [keyword, jobs, services, types, difficulties])

  // 반응형 width 감지
  const { isTablet } = useResponsiveWidth()
  const [layout, setLayout] = useState<'grid' | 'justify'>('grid')
  const learningLayout = useLearninglayout()

  useEffect(() => {
    isTablet ? setLayout('justify') : setLayout(learningLayout)
  }, [isTablet, layout, learningLayout])

  if (isFetching) return <Loading />
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
