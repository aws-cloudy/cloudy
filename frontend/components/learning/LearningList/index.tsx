'use client'

import React, { useCallback, useEffect, useRef, useState } from 'react'
import styles from './LearningList.module.scss'
import LearningCard from '@/components/common/LearningCard'
import { useLearninglayout } from '@/stores/search'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { IFilter, ILearningCard } from '@/types/learning'
import { LEARNING_ROWS_PER_PAGE } from '@/constants/rows'
import { getLearnings } from '@/apis/learning'
import { useInView } from 'react-intersection-observer'
import Spinner from '@/components/common/Spinner'
import { useDifficultyFilter, useServiceFilter, useTypeFilter, usejobFilter } from '@/stores/learning'

const LearningList = (props: { data: ILearningCard[] }) => {
  const { data } = props

  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const difficulties = useDifficultyFilter()

  // 무한 스크롤
  const [offset, setOffset] = useState<number>(2)
  const [list, setList] = useState<ILearningCard[]>(data)
  const preventRef = useRef<boolean>(true) // 중복 방지
  const { ref, inView } = useInView({ threshold: 1 })
  const [hasMore, setHasMore] = useState<boolean>(true)

  const loadMoreLearning = useCallback(async () => {
    if (!hasMore) return

    // const apiLearnings = await getLearnings(offset, LEARNING_ROWS_PER_PAGE)

    if (offset > 5) setHasMore(false)
    // if (apiLearnings.length < LEARNING_ROWS_PER_PAGE) {
    //   setHasMore(false) // 더 이상 로드할 데이터가 없을 때
    // }
    preventRef.current = true

    // setList([...list, ...apiLearnings])
    await new Promise(resolve => setTimeout(resolve, 1000))
    console.log('직무', jobs)
    setOffset(offset + 1)
  }, [offset, hasMore, jobs, services, types, difficulties])

  useEffect(() => {
    if (inView && hasMore && preventRef.current) {
      loadMoreLearning()
      preventRef.current = false
    }
  }, [inView, hasMore, loadMoreLearning])

  // 필터링
  useEffect(() => {
    setList(data)
    setOffset(2)
    setHasMore(true)
    loadMoreLearning()
  }, [data, jobs, services, types, difficulties])

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
      {hasMore && <Spinner Spinnerref={ref} />}
    </>
  )
}

export default LearningList
