'use client'

import { useEffect, useState } from 'react'
import styles from './MainSearch.module.scss'
import { BiSearch } from 'react-icons/bi'
import { useForm } from 'react-hook-form'

function MainSearch() {
  const [isOpen, setIsOpen] = useState<boolean>(false)
  // const [keyword, setKeyword] = useState<string>('')
  const { register, watch, handleSubmit } = useForm<{ search: string }>()

  const keyword = watch('search', '')

  const innerHtml = (word: string) => {
    return {
      __html: word.replace(keyword, matched => {
        return `<span>${matched}</span>`
      }),
    }
  }

  const handleChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
    await new Promise(resolve => setTimeout(resolve, 5000))
  }

  const onSubmit = (data: any) => console.log(data)

  useEffect(() => {
    if (!keyword) return
    if (keyword.length > 0) {
      setIsOpen(true)
    } else {
      setIsOpen(false)
    }
  }, [keyword])

  // useEffect(() => {
  //   const subscirbe = watch((data, { name }) => {
  //     setKeyword(data.search)
  //     console.log(data, name)
  //   })
  //   //모든 input 데이터를 담은 객체 data, change 이벤트가 발생하고 있는 input의 name을 인자로 받는 콜백함수
  //   return () => subscirbe.unsubscribe()
  // }, [watch])

  return (
    <div
      className={styles.container}
      onBlur={() => setIsOpen(false)}
      onFocus={() => {
        if (keyword) setIsOpen(true)
      }}
    >
      <div className={styles.searchBox}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <input type="text" className={styles.searchInput} {...register('search')} onChange={handleChange} />
        </form>
        <BiSearch className={styles.searchIcon} />
        {isOpen && (
          <div className={styles.searchItemBox}>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('안녕')}></div>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('안녕하세요')}></div>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('안녕하세요1')}></div>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('안녕하세요2')}></div>
            <div className={styles.searchItem} dangerouslySetInnerHTML={innerHtml('안녕하세요3')}></div>
          </div>
        )}
      </div>
    </div>
  )
}

export default MainSearch
