'use client'
import React, { useState } from 'react'
import styles from './DetailTop.module.scss'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import Avatar from '@/components/common/Avatar'
import { IRoadmapCard } from '@/types/roadmap'
import { deleteBookmark, postBookmark } from '@/apis/bookmark'

const DetailTop = ({ data, bookId }: { data: IRoadmapCard; bookId: number | null }) => {
  const [clickMark, setClickMark] = useState(Boolean(bookId))
  const [bookmarkId, setBookmarkId] = useState<number | null>(bookId)

  const handleMarkClear = async () => {
    if (bookmarkId === null) return

    try {
      await deleteBookmark(bookmarkId)
      setClickMark(false)
      setBookmarkId(null)
    } catch (error) {
      console.error('스크랩 해제 실패하였습니다.', error)
    }
  }

  const handleMarkSelect = async () => {
    try {
      await postBookmark(data.roadmapId)
      setClickMark(true)
    } catch (error) {
      console.error('스크랩 실패하였습니다.', error)
    }
  }

  return (
    <>
      <div className={styles.tags}>
        <div>#{data.job}</div>
        <div>#{data.service}</div>
      </div>
      <div className={styles.title}>
        {data.title}
        {clickMark ? (
          <BsBookmarkFill
            className={styles.bookmark}
            onClick={handleMarkClear}
            role="button"
            data-testid="scrap-icon"
          />
        ) : (
          <BsBookmark className={styles.bookmark} onClick={handleMarkSelect} role="button" data-testid="unscrap-icon" />
        )}
      </div>
      <div className={styles.profile}>
        <div className={styles.profileImage}>
          <Avatar Icon={BsChat} size="small" />
        </div>
        <div className={styles.name}>클라우디</div>
      </div>
    </>
  )
}

export default DetailTop
