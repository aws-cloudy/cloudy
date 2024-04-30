'use client'

import React, { useCallback, useEffect, useRef, useState } from 'react'
import styles from './LearningList.module.scss'
import LearningCard from '@/components/common/LearningCard'
import { useLearninglayout } from '@/stores/search'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { ILearningCard } from '@/types/learning'
import { LEARNING_ROWS_PER_PAGE } from '@/constants/rows'
import { getLearnings } from '@/apis/learning'
import { useInView } from 'react-intersection-observer'
import Spinner from '@/components/common/Spinner'

const LearningList = (props: { data: ILearningCard[] }) => {
  const { data } = props

  // 무한 스크롤
  const [offset, setOffset] = useState<number>(2)
  const [list, setList] = useState<ILearningCard[]>(data)
  const preventRef = useRef<boolean>(true) // 중복 방지
  const { ref, inView } = useInView({ threshold: 1 })
  const [hasMore, setHasMore] = useState<boolean>(true)

  const loadMoreLearning = useCallback(async () => {
    if (!hasMore) return
    const apiLearnings = await getLearnings(offset, LEARNING_ROWS_PER_PAGE)

    if (apiLearnings.length < LEARNING_ROWS_PER_PAGE) {
      setHasMore(false) // 더 이상 로드할 데이터가 없을 때
    }
    preventRef.current = true

    setList([...list, ...apiLearnings])
    setOffset(offset + 1)
    console.log('불러와짐~')
  }, [offset, hasMore])

  useEffect(() => {
    if (inView && hasMore && preventRef.current) {
      loadMoreLearning()
      preventRef.current = false
    }
  }, [inView, hasMore, loadMoreLearning])

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
        {list.map(item => (
          <LearningCard key={item.learningId} item={item} layout={layout} />
        ))}
      </div>
      {hasMore && <Spinner Spinnerref={ref} />}
    </>
  )
}

export default LearningList
