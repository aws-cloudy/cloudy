'use client'

import { useState } from 'react'
import styles from './CustomSelect.module.scss'
import { IoIosArrowDown } from 'react-icons/io'
import { IoIosArrowUp } from 'react-icons/io'
import { ICustomSelect } from '@/types/common'
import { IFilter } from '@/types/learning'

const CustomSelect = (props: ICustomSelect) => {
  const { item, setItem, options } = props

  const [open, setOpen] = useState(false)

  const onChange = (v: IFilter) => {
    setItem(v)
    setOpen(false)
  }

  const isDefault = item.value === ''

  return (
    <div className={styles.select}>
      <div className={`${styles.selected} ${!isDefault && styles.isNotDefault}`} onClick={() => setOpen(!open)}>
        <span>{item.name}</span>
        {open ? (
          <IoIosArrowUp color={isDefault ? '#000' : '#fff'} />
        ) : (
          <IoIosArrowDown color={isDefault ? '#000' : '#fff'} />
        )}
      </div>
      <div className={`${styles.optionWrap} ${open ? styles.visible : styles.none} `} role="listbox">
        {options.map(v => (
          <div className={styles.option} onClick={() => onChange(v)} key={v.name}>
            {v.name}
          </div>
        ))}
      </div>
    </div>
  )
}

export default CustomSelect
