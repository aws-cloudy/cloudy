import React from 'react'
import { IconType } from 'react-icons'
import styles from './MainIntroduceItem.module.scss'

type Props = {
  title: string
  student: string
  Icon: IconType
}

function MainIntroduceItem({ title, student, Icon }: Props) {
  return (
    <div className={styles.container}>
      <div className={styles.textBox}>
        <div className={styles.title}>{title}</div>
        <div className={styles.student}>{student}</div>
      </div>
      {<Icon className={styles.icon} />}
    </div>
  )
}

export default MainIntroduceItem
