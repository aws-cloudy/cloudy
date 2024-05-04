'use client'

import React from 'react'
import styles from './CustomInput.module.scss'
import { ICustomInput } from '@/types/common'
import { IoMdSearch } from 'react-icons/io'

const CustomInput = (props: ICustomInput) => {
  const { value, setValue, width, onClick } = props

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => setValue(e.target.value)

  return (
    <div className={styles.container} style={{ width }}>
      <input
        value={value}
        onChange={onChange}
        placeholder="검색어를 입력해주세요"
        className={styles.input}
        name="custom-input"
      />
      <div className={styles.iconWrap} onClick={onClick}>
        <IoMdSearch color="#ccc" />
      </div>
    </div>
  )
}

export default CustomInput
