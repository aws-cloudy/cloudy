'use client'

import { commuURL } from '@/apis/urls'
import axios from 'axios'
import { useRouter } from 'next/navigation'
import React, { BaseSyntheticEvent } from 'react'
import { SubmitHandler, useForm } from 'react-hook-form'
import styles from './DetailAnswerInput.module.scss'
import Button from '@/components/common/Button'
import { useIsLogin } from '@/stores/authStore'

function DetailAnswerInput({ id }: { id: string | number }) {
  const { register, getValues, handleSubmit, reset } = useForm<{ desc: string }>()
  const router = useRouter()
  const isLogin = useIsLogin()

  const onSubmit: SubmitHandler<{ desc: string }> = async (
    _data,
    e: BaseSyntheticEvent<object, any, any> | undefined,
  ) => {
    if (e) e.preventDefault()
    const desc = getValues('desc')
    await axios.post(`${commuURL}question/answer`, { postId: id, desc })
    router.refresh()
    reset()
  }

  return (
    isLogin && (
      <form onSubmit={handleSubmit(onSubmit)} className={styles.container}>
        <textarea {...register('desc')} placeholder="댓글을 입력하세요" required className={styles.textBox}></textarea>
        <Button type="submit" width="25%">
          작성
        </Button>
      </form>
    )
  )
}

export default DetailAnswerInput
