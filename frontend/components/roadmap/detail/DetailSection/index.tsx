'use client'

import Image from 'next/image'
import styles from './DetailSection.module.scss'
import React, { useState } from 'react'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import Avatar from '@/components/common/Avatar'
import Description from '../Description/indext'
import CommentSection from '../CommentSection'

const DetailSection = () => {
  const [selectedTab, setSelectedTab] = useState('introduce')

  const comments = [
    {
      id: 1,
      name: '김싸피',
      date: '2024.04.15. 11:53',
      context: '이거대로 공부하고 자격증 합격했어요. 강추합니다 !!',
    },
    {
      id: 2,
      name: '이싸피',
      date: '2024.04.14. 11:53',
      context:
        '정말 좋은 로드맵인 것 같아요 ㅎㅎ 어떻게 시작해야할지 고민되었었는데 ㅎㅎㅎ 다른 분들도 꼭 합격하시길!!',
    },
  ]

  return (
    <section className={styles.section}>
      <div className={styles.tags}>
        <div>#DataWrangler</div>
        <div>#DataWrangler</div>
      </div>
      <div className={styles.title}>
        Analyze security findings faster with no-code data preparation using generative AI and Amazon SageMaker Canvas
        <BsBookmarkFill className={styles.bookmark} />
      </div>
      <div className={styles.profile}>
        <div className={styles.profileImage}>
          <Avatar Icon={BsChat} size="small" />
        </div>
        <div className={styles.name}>클라우디</div>
      </div>
      <div className={styles.row}>
        <div className={styles.left}>
          <div className={styles.tab}>
            <div
              className={selectedTab === 'introduce' ? styles.activeTab : styles.inActiveTab}
              onClick={() => {
                setSelectedTab('introduce')
              }}
            >
              로드맵 소개
            </div>
            <div
              className={selectedTab === 'course' ? styles.activeTab : styles.inActiveTab}
              onClick={() => {
                setSelectedTab('course')
              }}
            >
              로드맵 코스
            </div>
          </div>
          {selectedTab === 'introduce' && <Description />}
        </div>
        <div className={styles.right}>
          <CommentSection comments={comments} />
        </div>
      </div>
    </section>
  )
}

export default DetailSection
