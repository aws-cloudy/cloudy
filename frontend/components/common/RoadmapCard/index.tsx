'use client'

import React, { useState } from 'react'
import styles from './RoadmapCard.module.scss'
import Image from 'next/image'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'

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
        <Image src={road.image} alt="" className={styles.image} />
        {clickMark === 'scrap' ? (
          <BsBookmarkFill className={styles.bookmark} onClick={handleMarkClear} size={20} role="button" />
        ) : (
          <BsBookmark className={styles.bookmark} onClick={handleMarkSelect} size={20} role="button" />
        )}
      </div>
      <div className={styles.info}>
        <div className={styles.title}>{road.title}</div>
        <div className={styles.context}>
          {road.context.length > 50 ? `${road.context.substring(0, 50)}...` : road.context}
        </div>
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
