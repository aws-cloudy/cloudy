'use client'

import React, { useCallback, useEffect, useState } from 'react'
import styles from './LearningList.module.scss'
import LearningCard from '@/components/common/LearningCard'
import { useLearninglayout } from '@/stores/search'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { ILearningCard } from '@/types/learning'
import { LEARNING_ROWS_PER_PAGE } from '@/constants/rows'
import { getLearnings } from '@/apis/learning'
import { useDifficultyFilter, useServiceFilter, useTypeFilter, usejobFilter } from '@/stores/learning'
import Observer from '@/components/common/Observer'

const LearningList = (props: { data: ILearningCard[] }) => {
  const { data } = props

  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const difficulties = useDifficultyFilter()

  // 무한 스크롤
  const [offset, setOffset] = useState<number>(2)
  const [list, setList] = useState<ILearningCard[]>(data)
  const [hasMore, setHasMore] = useState<boolean>(true)

  const loadMoreLearning = useCallback(async () => {
    if (!hasMore) return

    const apiLearnings = await getLearnings(offset, LEARNING_ROWS_PER_PAGE)

    if (apiLearnings.length < LEARNING_ROWS_PER_PAGE) {
      setHasMore(false) // 더 이상 로드할 데이터가 없을 때
    }

    setList([...list, ...apiLearnings])
    setOffset(offset + 1)
  }, [offset, hasMore, jobs, services, types, difficulties])

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isIntersecting) {
      loadMoreLearning()
    }
  }

  useEffect(() => {
    setHasMore(true)
    setOffset(2)
    setList(data)
  }, [jobs, services, types, difficulties])

  // 반응형 width 감지
  const { isTablet } = useResponsiveWidth()
  const [layout, setLayout] = useState<'grid' | 'justify'>('grid')
  const learningLayout = useLearninglayout()

  useEffect(() => {
    isTablet ? setLayout('justify') : setLayout(learningLayout)
  }, [isTablet, layout, learningLayout])

  return (
    <>
      <div className={layout === 'grid' ? styles.gridContainer : styles.justifyContainer}>
        {list && list.map(item => <LearningCard key={item.learningId} item={item} layout={layout} />)}
      </div>
      <Observer callback={observerCallback} />
      {/* {hasMore && <Spinner Spinnerref={ref} />} */}
    </>
  )
}

export default LearningList
