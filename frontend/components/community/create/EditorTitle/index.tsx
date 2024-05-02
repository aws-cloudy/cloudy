'user client'

import styles from '@/components/community/CreateForm/CreateForm.module.scss'
import { UseFormRegister } from 'react-hook-form'
import React from 'react'

type Props = {
  register: UseFormRegister<{ title: string }>
  orig?: string
}

function EditorTitle({ register, orig }: Props) {
  const defaultValue = orig ? orig : ''

  return (
    <input
      type="text"
      className={styles.title}
      defaultValue={defaultValue}
      placeholder="제목을 입력하세요"
      {...register('title')}
    />
  )
}
export default React.memo(EditorTitle)
