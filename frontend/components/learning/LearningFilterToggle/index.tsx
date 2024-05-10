'use client'

import React, { useEffect, useState } from 'react'
import styles from './LearningFilterToggle.module.scss'
import { IoIosArrowDown } from 'react-icons/io'
import { IoIosArrowUp } from 'react-icons/io'
import { IFilter, ILearningFilterToggle } from '@/types/learning'
import LearningFilterToggoleItem from '../LearningFilterToggleItem'

const LearningFilterToggle = (props: ILearningFilterToggle) => {
  const { title, data, filter, setFilter } = props

  const [open, setOpen] = useState<boolean>(false)
  const [list, setList] = useState<IFilter[]>(filter)

  useEffect(() => {
    setFilter(list)
  }, [list])

  return (
    <div className={styles.container}>
      <div className={styles.wrap}>
        <div className={styles.title} onClick={e => setOpen(!open)}>
          <span>{title}</span>
          {open ? <IoIosArrowUp /> : <IoIosArrowDown />}
        </div>
        {open && (
          <>
            {data.map(v => (
              <LearningFilterToggoleItem key={v.name} item={v} list={list} setList={setList} />
            ))}
          </>
        )}
      </div>
    </div>
  )
}

export default LearningFilterToggle
