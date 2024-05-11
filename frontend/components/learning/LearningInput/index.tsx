import React, { useEffect, useState } from 'react'
import styles from './LearningInput.module.scss'
import { ILearningAutocomplete, ILearningInput } from '@/types/learning'
import LearningSearchList from '../LearningSearchList'
import { useRouter, useSearchParams } from 'next/navigation'
import { getSearchAutoComplete } from '@/apis/learning'

const LearningInput = (props: ILearningInput) => {
  const { value, setValue } = props

  const [isOpen, setIsOpen] = useState<boolean>(false)
  const [selected, setSelected] = useState<number>(-1)

  const [list, setList] = useState<ILearningAutocomplete[]>([])

  const handleKeyUp = async () => {
    const data = await getSearchAutoComplete(value)
    setList(data)
  }

  const router = useRouter()
  const params = useSearchParams()

  const job = params.get('job') || ''
  const service = params.get('service') || ''
  const type = params.get('type') || ''
  const difficulty = params.get('difficulty') || ''

  useEffect(() => {
    value ? setIsOpen(true) : setIsOpen(false)
  }, [value])

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value)
  }

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'ArrowDown') {
      setSelected(prev => (prev < list.length - 1 ? prev + 1 : list.length - 1))
    } else if (e.key === 'ArrowUp') {
      setSelected(prev => (prev > -1 ? prev - 1 : -1))
    } else if (e.key === 'Enter') {
      e.preventDefault()
      selected !== -1 ? handleSubmit(list[selected].title) : handleSubmit()
    } else if (!e.key.startsWith('Arrow')) {
      setSelected(-1)
    }
  }

  const handleSubmit = (title?: string) => {
    let url = `/learning?query=${title || value}`
    job && (url += `&job=${job}`)
    service && (url += `&service=${service}`)
    type && (url += `&type=${type}`)
    difficulty && (url += `&difficulty=${difficulty}`)
    setValue(title || value)
    router.push(url)
    setIsOpen(false)
  }

  return (
    <>
      <div
        className={styles.inputWrap}
        onBlur={() => setIsOpen(false)}
        onFocus={() => {
          if (value) setIsOpen(true)
        }}
      >
        <input
          className={styles.input}
          placeholder="무엇이든 검색해보세요 ..."
          value={value}
          onChange={onChange}
          onKeyUp={handleKeyUp}
          onKeyDown={handleKeyDown}
          onMouseEnter={() => setSelected(-1)}
          name="learning-input"
        />
        {isOpen && list.length > 0 && (
          <LearningSearchList list={list} setIsOpen={setIsOpen} selected={selected} setValue={setValue} />
        )}
      </div>
    </>
  )
}

export default LearningInput
