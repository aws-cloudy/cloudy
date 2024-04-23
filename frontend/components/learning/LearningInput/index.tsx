import React from 'react'
import styles from './LearningInput.module.scss'
import { ILearningInput } from '@/types/learning'
import LearningSearchList from '../LearningSearchList'

const LearningInput = (props: ILearningInput) => {
  const { value, setValue, keyword, setKeyword } = props

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value)
    setKeyword('')
  }

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      handleSubmit()
    }
  }

  const handleSubmit = () => {
    setKeyword(value)
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
        {value && keyword === '' && <LearningSearchList setKeyword={setKeyword} setValue={setValue} />}
      </div>
    </>
  )
}

export default LearningInput
