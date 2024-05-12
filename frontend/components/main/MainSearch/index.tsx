'use client'

import { useEffect, useRef, useState } from 'react'
import styles from './MainSearch.module.scss'
import { BiSearch } from 'react-icons/bi'
import { useForm } from 'react-hook-form'
import { getSearchAutoComplete } from '@/apis/learning'
import { useRouter } from 'next/navigation'
import { ILearningAutocomplete } from '@/types/learning'

function MainSearch() {
  const [isOpen, setIsOpen] = useState<boolean>(false)
  const [list, setList] = useState<ILearningAutocomplete[]>([])
  const { register, watch, handleSubmit } = useForm<{ search: string }>()
  const [selected, setSelected] = useState(-1)
  const serchResultRef = useRef<HTMLDivElement>(null)

  const router = useRouter()

  const keyword = watch('search', '')

  // match 함수
  const innerHtml = (word: string) => {
    const regex = new RegExp(`${keyword}`, 'i')

    return {
      __html: word.replace(regex, matched => {
        return `<span>${matched}</span>`
      }),
    }
  }

  const handleKeyUp = async () => {
    const data = await getSearchAutoComplete(keyword)
    setList(data)
  }

  const onSearch = (v: string) => {
    router.push('/learning' + '?query=' + v)
  }

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'ArrowDown') {
      setSelected(prev => (prev < list.length - 1 ? prev + 1 : list.length - 1))
    } else if (e.key === 'ArrowUp') {
      setSelected(prev => (prev > -1 ? prev - 1 : -1))
    } else if (e.key === 'Enter') {
      e.preventDefault()
      if (selected !== -1) {
        onSearch(list[selected].title)
      } else {
        onSearch(keyword)
      }
    } else if (!e.key.startsWith('Arrow')) {
      setSelected(-1)
    }
  }

  useEffect(() => {
    if (!keyword) return
    if (keyword.length > 0 && list.length > 0) {
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
        <form
          onSubmit={handleSubmit((...args) => {
            console.log(...args)
            onSearch(keyword)
          })}
        >
          <input
            type="text"
            className={styles.searchInput}
            {...register('search')}
            onKeyUp={handleKeyUp}
            onKeyDown={handleKeyDown}
            autoComplete="off"
          />
        </form>
        <BiSearch className={styles.searchIcon} />
        {isOpen && (
          <div className={styles.searchItemBox} ref={serchResultRef}>
            {list &&
              list.map((item, idx) => (
                <div
                  key={item.learningId}
                  className={`${styles.searchItem} ${selected === idx && styles.selected}`}
                  dangerouslySetInnerHTML={innerHtml(item.title)}
                  onMouseDown={() => {
                    onSearch(item.title)
                  }}
                />
              ))}
          </div>
        )}
      </div>
    </div>
  )
}

export default MainSearch
