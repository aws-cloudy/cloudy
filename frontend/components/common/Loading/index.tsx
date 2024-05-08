import React from 'react'
import styles from './Loading.module.scss'
import Image from 'next/image'

function Loading({ maxWidth }: { maxWidth?: boolean }) {
  return (
    <div className={`${styles.container} ${maxWidth && styles.maxWidth}`}>
      <Image src="/gif/spinner.gif" alt="로딩중" width="80" height="80" priority />
    </div>
  )
}

export default Loading
