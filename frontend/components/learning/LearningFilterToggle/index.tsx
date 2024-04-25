'use client'

import React, { useState } from 'react'
import styles from './LearningFilterToggle.module.scss'
import { IoIosArrowDown } from 'react-icons/io'
import { IoIosArrowUp } from 'react-icons/io'
import { FiPlus } from 'react-icons/fi'
import { FiMinus } from 'react-icons/fi'
import { IFilter } from '@/types/learning'
import { useLearningActions } from '@/stores/learning'

const LearningFilterToggle = (props: { title: string; data: IFilter[] }) => {
  const { title, data } = props

  const [open, setOpen] = useState<boolean>(false)

  const { setJobFilter, setServiceFilter, setTypeFilter, setDifficultyFilter } = useLearningActions()

  // 있다면 제거, 없다면  추가
  const onClick = (v: IFilter) => {
    switch (v.category) {
      case 'job':
        setJobFilter(v)
        break
      case 'service':
        setServiceFilter(v)
        break
      case 'type':
        setTypeFilter(v)
        break
      case 'difficulty':
        setDifficultyFilter(v)
        break
    }
  }

  return (
    <div className={styles.container}>
      <div className={styles.wrap}>
        <div className={styles.title} onClick={e => setOpen(!open)}>
          <span>{title}</span>
          {open ? <IoIosArrowUp /> : <IoIosArrowDown />}
        </div>
        {open && (
          <div className={styles.itemList}>
            {data.map(v => (
              <div className={styles.itemListItem} key={v.value}>
                <FiPlus onClick={e => onClick(v)} />
                <span>{v.name}</span>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  )
}

export default LearningFilterToggle
