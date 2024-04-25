import React, { useState } from 'react'
import styles from './RoadmapCard.module.scss'
import Image from 'next/image'
import img from '../../../public/img/learning/1.png'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'

const RoadmapCard = () => {
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
    <div className={styles.card}>
      <div className={styles.imageBox}>
        <Image src={img} alt="" className={styles.image} />
        {clickMark === 'scrap' ? (
          <BsBookmarkFill className={styles.bookmark} onClick={handleMarkClear} size={20} />
        ) : (
          <BsBookmark className={styles.bookmark} onClick={handleMarkSelect} size={20} />
        )}
      </div>
      <div className={styles.info}>
        <div className={styles.title}>
          AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide Guide{' '}
        </div>
        <div className={styles.context}>
          효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 알 수 있음 효과적인 프롬포트를 설계하기 위한 원칙,
          기법 및 모범 사례를...
        </div>
        <div className={styles.row}>
          <div className={styles.tags}>
            <div className={styles.tag}>#DataWrangler</div>
            <div className={styles.tag}>#GeneratedAI</div>
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
