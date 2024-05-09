import React from 'react'
import styles from './LearningInput.module.scss'
import { ILearningInput } from '@/types/learning'
import LearningSearchList from '../LearningSearchList'
import { useRouter, useSearchParams } from 'next/navigation'

const LearningInput = (props: ILearningInput) => {
  const { value, setValue } = props

  const router = useRouter()
  const params = useSearchParams()

  const keyword = params.get('query') || ''
  const job = params.get('job') || ''
  const service = params.get('service') || ''
  const type = params.get('type') || ''
  const difficulty = params.get('difficulty') || ''

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value)
  }

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      handleSubmit()
    }
  }

  const handleSubmit = () => {
    let url = `/learning?query=${value}`
    job && (url += `&job=${job}`)
    service && (url += `&service=${service}`)
    type && (url += `&type=${type}`)
    difficulty && (url += `&difficulty=${difficulty}`)
    router.push(url)
  }

  return (
    <>
      <div className={styles.inputWrap}>
        <input
          className={styles.input}
          placeholder="무엇이든 검색해보세요 ..."
          value={value}
          onChange={onChange}
          onKeyDown={handleKeyDown}
          name="learning-input"
        />
        {value && keyword === '' && <LearningSearchList setValue={setValue} />}
      </div>
    </>
  )
}

export default LearningInput
