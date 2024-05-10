import React, { useEffect, useState } from 'react'
import styles from './LearningInput.module.scss'
import { ILearningAutocomplete, ILearningInput } from '@/types/learning'
import LearningSearchList from '../LearningSearchList'
import { useRouter, useSearchParams } from 'next/navigation'

const LearningInput = (props: ILearningInput) => {
  const { value, setValue } = props

  const [isOpen, setIsOpen] = useState<boolean>(false)
  const [selected, setSelected] = useState<number>(-1)

  const [list, setList] = useState<ILearningAutocomplete[]>([])

  useEffect(() => {
    setList([
      {
        learningId: '61',
        title: 'Amazon ElastiCache Service Introduction (Korean)',
        documentId: '3',
      },
      {
        learningId: '74',
        title: 'Amazon ElastiCache Service Primer (Korean)',
        documentId: '28',
      },
    ])
  }, [])

  const router = useRouter()
  const params = useSearchParams()

  const keyword = params.get('query') || ''
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
      setIsOpen(false)
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
          onKeyDown={handleKeyDown}
          onMouseEnter={() => setSelected(-1)}
          name="learning-input"
        />
        {isOpen && <LearningSearchList list={list} setIsOpen={setIsOpen} selected={selected} setValue={setValue} />}
      </div>
    </>
  )
}

export default LearningInput
