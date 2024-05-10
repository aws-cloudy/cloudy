import React, { useState } from 'react'
import styles from './LearningFilterToggle.module.scss'
import { IoIosArrowDown } from 'react-icons/io'
import { IoIosArrowUp } from 'react-icons/io'
import { ILearningFilterToggle } from '@/types/learning'
import LearningFilterToggoleItem from '../LearningFilterToggleItem'

const LearningFilterToggle = (props: ILearningFilterToggle) => {
  const { title, data, filter, setFilter } = props

  const [open, setOpen] = useState<boolean>(false)

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
              <LearningFilterToggoleItem key={v.name} item={v} list={filter} setList={setFilter} />
            ))}
          </>
        )}
      </div>
    </div>
  )
}

export default LearningFilterToggle
