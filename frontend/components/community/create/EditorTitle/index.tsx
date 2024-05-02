'user client'

import styles from '@/app/community/create/page.module.scss'
import { UseFormGetValues, UseFormRegister } from 'react-hook-form'
import React from 'react'

type Props = { register: UseFormRegister<{ title: string }>; getValues: UseFormGetValues<{ title: string }> }

function EditorTitle({ register, getValues }: Props) {
  return <input type="text" className={styles.title} placeholder="제목을 입력하세요" {...register('title')} />
}
export default React.memo(EditorTitle)
