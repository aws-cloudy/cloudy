'use client'
import Image from 'next/image'
import styles from './Detail.module.scss'
import React, { useState } from 'react'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import Avatar from '@/components/common/Avatar'
import Description from './Description/indext'
const Detail = () => {
  const [selectedTab, setSelectedTab] = useState('introduce')

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
        <div className={styles.right}>댓글</div>
      </div>
    </section>
  )
}

export default Detail
