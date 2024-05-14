'use client'

import React, { useEffect, useState } from 'react'
import styles from './RoadmapCard.module.scss'
import Image from 'next/image'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import { getShortText } from '@/utils/getShortText'
import { IRoadmapCard } from '@/types/roadmap'
import { useRouter } from 'next/navigation'
import { useSession } from 'next-auth/react'
import { deleteBookmark, postBookmark } from '@/apis/bookmark'

const RoadmapCard = (props: { item: IRoadmapCard }) => {
  const { item } = props

  const { data: session, status } = useSession()

  const [clickMark, setClickMark] = useState(item.isScrapped)

  const handleMarkClear = async (event: { stopPropagation: () => void }) => {
    event.stopPropagation()
    try {
      await deleteBookmark(item.bookmarkId)
      setClickMark(false)
    } catch (error) {
      console.error('스크랩 해제 실패하였습니다.', error)
    }

    //북마크 스크랩 해제
    setClickMark(false)
  }

  const handleMarkSelect = async (event: { stopPropagation: () => void }) => {
    event.stopPropagation()
    try {
      await postBookmark(item.roadmapId)
      setClickMark(true)
    } catch (error) {
      console.error('스크랩 실패하였습니다.', error)
    }

    //북마크 스크랩
    setClickMark(true)
  }

  useEffect(() => {
    setClickMark(item.isScrapped)
  }, [item.isScrapped])

  const router = useRouter()
  const onClick = () => router.push(`/roadmap/${item.roadmapId}`)

  if (item.isUseMypage && !clickMark) {
    return null
  }
  return (
    <div className={styles.card} key={item.roadmapId} onClick={onClick} data-testid="roadmap-item">
      <div className={styles.imageBox}>
        <Image src={item.thumbnail} alt="roadmap-image" className={styles.image} fill priority sizes="auto" />
        {status === 'authenticated' &&
          (clickMark ? (
            <BsBookmarkFill
              className={styles.bookmark}
              onClick={handleMarkClear}
              size={20}
              role="button"
              data-testid="scrap-icon"
            />
          ) : (
            <BsBookmark
              className={styles.bookmark}
              onClick={handleMarkSelect}
              size={20}
              role="button"
              data-testid="unscrap-icon"
            />
          ))}
      </div>
      <div className={styles.info}>
        <div className={styles.title}>{item.title}</div>
        <div className={styles.context}>{getShortText(item.summary, 50)}</div>
        <div className={styles.row}>
          <div className={styles.tags}>
            <div># {item.job}</div>
            <div># {item.service}</div>
          </div>
          <div className={styles.comment}>
            <BsChat className={styles.comment} />
            <span>{item.commentsCnt}</span>
          </div>
        </div>
      </div>
    </div>
  )
}

export default RoadmapCard
