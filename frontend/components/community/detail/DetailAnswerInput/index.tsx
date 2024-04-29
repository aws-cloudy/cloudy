'use client'

import { commuURL } from '@/apis/urls'
import { IQuestionAnswer } from '@/types/community'
import axios from 'axios'
import { useRouter } from 'next/navigation'
import React, { BaseSyntheticEvent } from 'react'
import { SubmitHandler, useForm } from 'react-hook-form'

function DetailAnswerInput({ id }: { id: string | number }) {
  const { register, getValues, handleSubmit, reset } = useForm<{ desc: string }>()
  const router = useRouter()

  const onSubmit: SubmitHandler<{ desc: string }> = async (
    _data,
    e: BaseSyntheticEvent<object, any, any> | undefined,
  ) => {
    if (e) e.preventDefault()
    const desc = getValues('desc')
    const res = await axios.post(`${commuURL}/question/answer`, { postId: id, desc })
    router.refresh()
    reset()
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <textarea {...register('desc')} placeholder="덧글을 입력하세요" required></textarea>
      <button type="submit">작성</button>
    </form>
  )
}

export default DetailAnswerInput
