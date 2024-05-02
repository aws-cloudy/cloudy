'use client'

import React, { useState } from 'react'
import styles from './RoadmapCard.module.scss'
import Image from 'next/image'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import { getShortText } from '@/utils/getShortText'
import { IRoadmapCard } from '@/types/roadmap'

const RoadmapCard = (props: { item: IRoadmapCard }) => {
  const { item } = props

  const [clickMark, setClickMark] = useState('scrap')

  const handleMarkClear = () => {
    //북마크 스크랩 해제
    setClickMark('unscrap')
  }

  const handleMarkSelect = () => {
    //북마크 스크랩
    setClickMark('scrap')
  }

  return (
    <div className={styles.card} key={item.roadmapId}>
      <div className={styles.imageBox}>
        <Image src={item.thumbnail} alt="roadmap-image" className={styles.image} fill priority sizes="auto" />
        {clickMark === 'scrap' ? (
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
        )}
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
            {item.commentsCnt}
          </div>
        </div>
      </div>
    </div>
  )
}

export default RoadmapCard
