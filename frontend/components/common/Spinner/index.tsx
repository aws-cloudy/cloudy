import React, { LegacyRef } from 'react'
import styles from './Spinner.module.scss'
import Image from 'next/image'

const Spinner = (props: { Spinnerref: LegacyRef<HTMLDivElement> | null }) => {
  const { Spinnerref } = props
  return (
    <div ref={Spinnerref && Spinnerref} className={styles.container}>
      <Image src="/gif/spinner.gif" alt="로딩중" width="80" height="80" />
    </div>
  )
}

export default Spinner
