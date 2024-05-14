'use client'

import React, { useEffect, useRef, useState } from 'react'
import styles from './LearningList.module.scss'

import LearningCard from '@/components/common/LearningCard'
import { useLearninglayout } from '@/stores/layout'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { ILearningCard } from '@/types/learning'
import { LEARNING_ROWS_PER_PAGE } from '@/constants/rows'
import { getFinalSearch, getLearnings } from '@/apis/learning'
import Observer from '@/components/common/Observer'
import Loading from '@/components/common/Loading'
import Empty from '@/components/common/Empty'
import { useSearchParams } from 'next/navigation'
import { useLearningActions, useLearningOriginalQuery } from '@/stores/learning'

const LearningList = () => {
  // 무한 스크롤
  const offset = useRef<number>(1)
  const hasMore = useRef<boolean>(true)
  const [list, setList] = useState<ILearningCard[]>([])
  const [isFetching, setIsFetching] = useState<boolean>(true)
  const params = useSearchParams()

  // 원본 검색어
  const originalQuery = useLearningOriginalQuery()
  const { setLearningOriginalQuery } = useLearningActions()

  // fetch
  const fetchLearnings = async () => {
    if (!hasMore.current) return

    const oKeyword = params.get('oquery')
    const keyword = params.get('query') || ''
    const job = params.get('job') || ''
    const service = params.get('service') || ''
    const type = params.get('type') || ''
    const difficulty = params.get('difficulty') || ''

    const final = await getFinalSearch(keyword)

    let word = ''
    if (oKeyword) {
      word = oKeyword
    } else if (final.modifiedQuery === undefined || originalQuery) {
      // 수정할 검색어가 없을 때
      word = keyword
    } else {
      word = final.modifiedQuery
      setLearningOriginalQuery(final.modifiedQuery)
    }

    const learnings = await getLearnings(offset.current, LEARNING_ROWS_PER_PAGE, word, job, service, type, difficulty)

    if (learnings) {
      learnings.length < LEARNING_ROWS_PER_PAGE && (hasMore.current = false) // 더 이상 로드할 데이터가 없을 때
      setList(prev => [...prev, ...learnings])
      offset.current += 1
    }

    setIsFetching(false)
  }

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isIntersecting && hasMore.current && offset.current > 1) {
      fetchLearnings()
    }
  }

  useEffect(() => {
    setIsFetching(true)
    window.scrollTo(0, 0) // 맨 위로 이동
    setList([])
    hasMore.current = true
    offset.current = 1
    setLearningOriginalQuery('')
    fetchLearnings()
  }, [params])

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
        {list && list.map((item, i) => <LearningCard key={i} item={item} layout={layout} />)}
      </div>
      <Observer callback={observerCallback} />
    </>
  )
}

export default LearningList
