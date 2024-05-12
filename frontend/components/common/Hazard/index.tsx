import React from 'react'
import styles from './Hazard.module.scss'
import Image from 'next/image'

const Hazard = () => {
  return (
    <div className={styles.container}>
      <div className={styles.imgWrap}>
        <Image src="/img/hazard.webp" alt="hazard" fill sizes="auto" priority />
      </div>
      <div className={styles.title}>검색어에 부적절한 컨텐츠가 포함되어 있습니다</div>
      <div className={styles.desc}>다른 검색어는 어떠세요?</div>
    </div>
  )
}

export default Hazard
