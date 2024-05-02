import React from 'react'
import styles from './Loading.module.scss'
import Image from 'next/image'

function Loading() {
  return (
    <div className={styles.container}>
      <Image src="/gif/spinner.gif" alt="로딩중" width="80" height="80" />
    </div>
  )
}

export default Loading
