import styles from './EditorHashtag.module.scss'
import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import { IEditorHashtag } from '@/types/communityProps'

function EditorHashtag({ tags, setTags }: IEditorHashtag) {
  const [isAdding, setIsAdding] = useState(false)
  const { register, getValues, setValue } = useForm<{ hash: string }>()

  const onKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    const tag = getValues('hash').toLowerCase()

    if (e.key === ' ' || e.key === 'Enter') {
      e.preventDefault()
      if (tag.length !== 0 && tags.length < 5) {
        if (tags.filter(e => e.title === tag).length === 0) {
          setTags(prev => [...prev, { id: null, title: tag }])
        }
        setValue('hash', '')
      }
      setIsAdding(false)
    } else if (e.key === 'Escape') {
      e.preventDefault()
      setValue('hash', '')
      setIsAdding(false)
    } else if (e.key === '#') {
      e.preventDefault()
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
        <input type="text" {...register('hash')} onKeyDown={onKeyDown} defaultValue="" maxLength={20} minLength={1} />
      ) : (
        <button className={styles.addbutton} type="button" onClick={() => setIsAdding(true)}>
          + 태그추가
        </button>
      )}
    </div>
  )
}

export default EditorHashtag
