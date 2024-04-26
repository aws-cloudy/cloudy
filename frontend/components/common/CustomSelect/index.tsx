'use client'

import { useState } from 'react'
import styles from './CustomSelect.module.scss'
import { IoIosArrowDown } from 'react-icons/io'
import { IoIosArrowUp } from 'react-icons/io'

const CustomSelect = () => {
  const [open, setOpen] = useState(false)
  const [value, setValue] = useState<string>('Developer')

  const onChange = (v: string) => {
    setValue(v)
    setOpen(false)
  }

  return (
    <div className={styles.select}>
      <div className={styles.selected} onClick={e => setOpen(!open)}>
        <div>{value}</div>
        {open ? <IoIosArrowUp /> : <IoIosArrowDown />}
      </div>
      <div className={`${styles.optionWrap} ${open ? styles.visible : styles.none}`}>
        <div className={styles.option} onClick={e => onChange('직무1')}>
          직무1
        </div>
        <div className={styles.option} onClick={e => onChange('직무2')}>
          직무2
        </div>
        <div className={styles.option} onClick={e => onChange('직무3')}>
          직무3
        </div>
        <div className={styles.option} onClick={e => onChange('직무4')}>
          직무4
        </div>
      </div>
    </div>
  )
}

export default CustomSelect
