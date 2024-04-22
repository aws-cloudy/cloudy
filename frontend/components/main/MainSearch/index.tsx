'use client'

import { useEffect, useState } from 'react'
import styles from './MainSearch.module.scss'
import { BiSearch } from 'react-icons/bi'

function MainSearch() {
  const [isOpen, setIsOpen] = useState(false)
  const [text, setText] = useState('')

  useEffect(() => {
    if (text.length > 0) {
      setIsOpen(true)
    } else {
      setIsOpen(false)
    }
  }, [text])

  return (
    <div
      className={styles.container}
      onBlur={() => setIsOpen(false)}
      onFocus={() => {
        if (text.length > 0) setIsOpen(true)
      }}
    >
      <div className={styles.searchBox}>
        <input
          type="text"
          className={styles.searchInput}
          value={text}
          onChange={e => {
            setText(e.target.value)
          }}
        />
        <BiSearch className={styles.searchIcon} />
        {isOpen && (
          <div className={styles.searchItemBox}>
            <div className={styles.searchItem}>{text}테스트</div>
            <div className={styles.searchItem}>{text}테스트</div>
            <div className={styles.searchItem}>{text}테스트</div>
            <div className={styles.searchItem}>{text}테스트</div>
            <div className={styles.searchItem}>{text}테스트</div>
          </div>
        )}
      </div>
    </div>
  )
}

export default MainSearch
