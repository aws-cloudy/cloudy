'use client'

import React, { useEffect, useRef, useState } from 'react'
import styles from './MainLectureRecomInner.module.scss'
import { ILearningCard } from '@/types/learning'
import LearningCard from '@/components/common/LearningCard'

function MainLectureRecomInner({ learningList, len }: { learningList: ILearningCard[]; len: number }) {
  const scrollRef = useRef<HTMLDivElement>(null)
  const [isMouseDown, setIsMouseDown] = useState(false)
  const prevX = useRef(0)

  const onMouseMove = (e: MouseEvent) => {
    const scroll = scrollRef.current
    if (!scroll) return
    console.log('mousemove')
    scroll.scrollTo({ left: scroll.scrollLeft + (prevX.current - e.pageX) })
    prevX.current = e.pageX
  }
  const onMouseUp = (e: MouseEvent) => {
    e.preventDefault()
    console.log('mouseup')
    window.removeEventListener('mousemove', onMouseMove)
    setIsMouseDown(false)
  }

  useEffect(() => {
    if (window.innerWidth < 992) return
    if (isMouseDown) {
      window.addEventListener('mousemove', onMouseMove)
      window.addEventListener('mouseup', onMouseUp)
      return () => {
        window.removeEventListener('mouseup', onMouseUp)
        window.removeEventListener('mousemove', onMouseMove)
      }
    }
  }, [isMouseDown])

  return (
    <div
      className={styles.container}
      ref={scrollRef}
      onMouseDown={e => {
        prevX.current = e.pageX
        setIsMouseDown(true)
      }}
    >
      <div className={styles.inner} style={{ width: `${len * 25}%` }}>
        {learningList &&
          learningList.map(each => (
            <div className={styles.item} key={each.learningId}>
              <LearningCard item={each} layout="grid" />
            </div>
          ))}
      </div>
    </div>
  )
}

export default MainLectureRecomInner
