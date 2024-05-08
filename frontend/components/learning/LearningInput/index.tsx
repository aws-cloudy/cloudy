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

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value)
  }

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      handleSubmit()
    }
  }

  const handleSubmit = () => {
    router.push('/learning' + '?query=' + value)
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
