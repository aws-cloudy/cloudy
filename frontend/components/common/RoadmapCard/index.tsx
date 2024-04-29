'use client'

import React, { useState } from 'react'
import styles from './RoadmapCard.module.scss'
import Image from 'next/image'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import { getShortText } from '@/utils/getShortText'

const RoadmapCard = ({ road }: any) => {
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
    <div className={styles.card} key={road.id}>
      <div className={styles.imageBox}>
        <Image src={road.image} alt="roadmap-image" className={styles.image} priority />
        {clickMark === 'scrap' ? (
          <BsBookmarkFill className={styles.bookmark} onClick={handleMarkClear} size={20} role="button" />
        ) : (
          <BsBookmark className={styles.bookmark} onClick={handleMarkSelect} size={20} role="button" />
        )}
      </div>
      <div className={styles.info}>
        <div className={styles.title}>{road.title}</div>
        <div className={styles.context}>{getShortText(road.context, 50)}</div>
        <div className={styles.row}>
          <div className={styles.tags}>
            {road.tags.map((tag: any) => (
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
