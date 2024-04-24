'use client'

import { useEffect, useMemo, useState } from 'react'
import styles from './MainSearch.module.scss'
import { BiSearch } from 'react-icons/bi'
import { useForm } from 'react-hook-form'

function MainSearch() {
  const [isOpen, setIsOpen] = useState(false)
  const { register, watch } = useForm<{ search: string }>()
  const keyword = watch('search', '')
  const innerHtml = (word: string) => {
    return {
      __html: word.replace(keyword, matched => {
        return `<span>${matched}</span>`
      }),
    }
  }

  useEffect(() => {
    if (!keyword) return
    if (keyword.length > 0) {
      setIsOpen(true)
    } else {
      setIsOpen(false)
    }
  }, [keyword])

  return (
    <div
      className={styles.container}
      onBlur={() => setIsOpen(false)}
      onFocus={() => {
        if (keyword) setIsOpen(true)
      }}
    >
      <div className={styles.searchBox}>
        <form>
          <input type="text" className={styles.searchInput} {...register('search')} />
        </form>
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
