import React from 'react'
import styles from './Empty.module.scss'

const Empty = ({ text }: { text: string }) => {
  return <div className={styles.container}>{text}</div>
}

export default Empty
