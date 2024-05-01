'use client'

import React, { useEffect, useState } from 'react'
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
  const [offset, setOffset] = useState<number>(1)
  const [list, setList] = useState<ILearningCard[]>([])
  const [hasMore, setHasMore] = useState<boolean>(true)
  const [isFetching, setIsFetching] = useState<boolean>(true)

  // fetch
  const fetchLearning = async (type: string) => {
    if (!hasMore) return

    const apiLearnings = await getLearnings(
      offset,
      LEARNING_ROWS_PER_PAGE,
      keyword,
      getTextFilter(jobs),
      getTextFilter(services),
      getTextFilter(types),
      getTextFilter(difficulties),
    )

    if (type === 'scroll' && apiLearnings.length < LEARNING_ROWS_PER_PAGE) {
      setHasMore(false) // 더 이상 로드할 데이터가 없을 때
      console.log('hasMore 변경됨~~')
    }

    setList(prev => [...prev, ...apiLearnings])
    setOffset(offset + 1)
    setIsFetching(false)
  }

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isIntersecting && list.length > 0) {
      fetchLearning('scroll')
    }
  }

  useEffect(() => {
    window.scrollTo(0, 0) // 맨 위로 이동
    setList([])
    setHasMore(true)
    setOffset(1)
    fetchLearning('')
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
        <div>{list && list.length}</div>
        {list && list.map(item => <LearningCard key={item.learningId} item={item} layout={layout} />)}
      </div>
      <Observer callback={observerCallback} />
    </>
  )
}

export default LearningList
