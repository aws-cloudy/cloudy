'use client'

import React from 'react'
import styles from './CustomInput.module.scss'
import { ICustomInput } from '@/types/common'
import { IoMdSearch } from 'react-icons/io'

const CustomInput = (props: ICustomInput) => {
  const { value, setValue, width, onSearch } = props
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => setValue(e.target.value)

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') onSearch()
  }

  return (
    <div className={styles.container} style={{ width }}>
      <input
        value={value}
        onChange={onChange}
        placeholder="검색어를 입력해주세요"
        className={styles.input}
        name="custom-input"
        onKeyDown={handleKeyDown}
      />
      <div className={styles.iconWrap} onClick={onSearch}>
        <IoMdSearch color="#ccc" />
      </div>
    </div>
  )
}

export default CustomInput
