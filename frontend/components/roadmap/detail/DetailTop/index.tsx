'use client'
import React, { useEffect, useState } from 'react'
import styles from './DetailTop.module.scss'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import Avatar from '@/components/common/Avatar'
import { IRoadmapCard } from '@/types/roadmap'
import { deleteBookmark, postBookmark } from '@/apis/bookmark'

const DetailTop = ({ data }: { data: IRoadmapCard }) => {
  const [clickMark, setClickMark] = useState('unscrap')

  const handleMarkClear = async () => {
    try {
      await deleteBookmark(data.roadmapId)
      setClickMark('unscrap')
    } catch (error) {
      console.error('스크랩 해제 실패하였습니다.', error)
    }
    setClickMark('unscrap')
  }

  const handleMarkSelect = async () => {
    try {
      await postBookmark(data.roadmapId)
      setClickMark('scrap')
    } catch (error) {
      console.error('스크랩 실패하였습니다.', error)
    }
    setClickMark('scrap')
  }

  return (
    <>
      <div className={styles.tags}>
        <div>#{data.job}</div>
        <div>#{data.service}</div>
      </div>
      <div className={styles.title}>
        {data.title}
        {clickMark === 'scrap' ? (
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
