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
    <div className={styles.card} key={item.id}>
      <div className={styles.imageBox}>
        <Image src={item.image} alt="roadmap-image" className={styles.image} fill priority sizes="auto" />
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
        <div className={styles.context}>{getShortText(item.context, 50)}</div>
        <div className={styles.row}>
          <div className={styles.tags}>
            {item.tags.map(tag => (
              <div key={tag}>#{tag}</div>
            ))}
          </div>
          <div className={styles.comment}>
            <BsChat className={styles.comment} /> 2
          </div>
        </div>
      </div>
    </div>
  )
}

export default RoadmapCard
