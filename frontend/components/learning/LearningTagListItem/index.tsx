import React from 'react'
import styles from './LearningTagListItem.module.scss'
import { FaCheck } from 'react-icons/fa6'

const LearningTagListItem = (props: { text: string }) => {
  const { text } = props
  return (
    <div className={styles.container}>
      <FaCheck color="#FF9900" />
      <span>{text}</span>
    </div>
  )
}

export default LearningTagListItem
