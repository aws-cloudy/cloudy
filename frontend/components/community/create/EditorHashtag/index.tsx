import styles from './EditorHashtag.module.scss'
import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import { IEditorHashtag } from '@/types/communityProps'

function EditorHashtag({ tags, setTags }: IEditorHashtag) {
  const [isAdding, setIsAdding] = useState(false)
  const { register, handleSubmit, getValues, reset } = useForm<{ hash: string }>()

  const onSpacebar = (e: React.KeyboardEvent<HTMLInputElement>) => {
    const tag = getValues('hash')
    if (e.key === ' ' || e.key === 'Enter') {
      e.preventDefault()
      if (tag.length !== 0 && tags.length < 5) {
        if (tags.filter(e => e.title === tag).length === 0) {
          setTags(prev => [...prev, { id: null, title: tag }])
        }
        reset()
      }
      setIsAdding(false)
    } else if (e.key === 'Escape') {
      e.preventDefault()
      reset()
      setIsAdding(false)
    }
  }

  return (
    <div className={styles.container}>
      {tags.map((e, idx) => (
        <span
          key={idx}
          onClick={() => {
            setTags(prev => prev.filter(ea => ea.title !== e.title))
          }}
          className={styles.tag}
        >
          #{e.title}
        </span>
      ))}
      {isAdding ? (
        <input type="text" {...register('hash')} onKeyDown={onSpacebar} defaultValue="" maxLength={20} minLength={1} />
      ) : (
        <button className={styles.addbutton} type="button" onClick={() => setIsAdding(true)}>
          + 태그추가
        </button>
      )}
    </div>
  )
}

export default EditorHashtag
