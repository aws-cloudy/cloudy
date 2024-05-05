'use client'

import React, { useState } from 'react'
import styles from './DetailTab.module.scss'
import DetailInfo from '../DetailInfo'
import DetailCourse from '../DetailCourse'

const DetailTab = () => {
  const [selectedTab, setSelectedTab] = useState<number>(0)

  return (
    <>
      <div className={styles.tab}>
        <div className={!selectedTab ? styles.activeTab : styles.inActiveTab} onClick={() => setSelectedTab(0)}>
          로드맵 소개
        </div>
        <div className={selectedTab ? styles.activeTab : styles.inActiveTab} onClick={() => setSelectedTab(1)}>
          로드맵 코스
        </div>
      </div>
      {!selectedTab ? <DetailInfo /> : <DetailCourse />}
    </>
  )
}

export default DetailTab
