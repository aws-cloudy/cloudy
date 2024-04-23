'use client'

import { useEffect, useState } from 'react'
import styles from './MainSearch.module.scss'
import { BiSearch } from 'react-icons/bi'

function MainSearch() {
  const [isOpen, setIsOpen] = useState(false)
  const [text, setText] = useState('')
  const replacer = new RegExp(text, 'gi')
  const innerHtml = (word: string) => {
    return {
      __html: word.replace(replacer, matched => {
        return `<span>${matched}</span>`
      }),
    }
  }

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
            setText(`${e.target.value}`)
          }}
        />
        <BiSearch className={styles.searchIcon} />
        {isOpen && (
          <div className={styles.searchItemBox}>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('안녕')}></div>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('안녕하세요')}></div>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('안녕이라말하지마')}></div>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('그런눈빛으로내게말하지마')}></div>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('모나리자같은표정을하고')}></div>
          </div>
        )}
      </div>
    </div>
  )
}

export default MainSearch
