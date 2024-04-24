'use client'

import { useChatbotActions, useChatbotType } from '@/stores/chatbotStore'
import styles from './ChatRoom.module.scss'
import { LuDog } from 'react-icons/lu'
import { LuCat } from 'react-icons/lu'
import { chatSample } from '../dummy'
import ChatRoomMessage from '../ChatRoomMessage'
import React, { useEffect, useRef, useState } from 'react'
import { useForm } from 'react-hook-form'

function ChatRoom() {
  const chatBotType = useChatbotType()
  const msgRef = useRef<HTMLDivElement>(null)
  const { setChatbotType } = useChatbotActions()
  const ico = chatBotType === 'oudy' ? LuDog : LuCat
  const [messages, setMessages] = useState(chatSample)
  const { register, handleSubmit, getValues, reset, watch } = useForm<{ msg: string }>()

  const onSubmit = () => {
    const content = getValues('msg')
    if (content === '') return
    setMessages(prev => [...prev, { sender: 'user', content }])
    reset()
  }

  useEffect(() => {
    const messageBox = msgRef.current
    if (!messageBox) return
    messageBox.scrollTo({ top: messageBox.scrollHeight })
  }, [messages])

  return (
    <div className={styles.container}>
      <div className={styles.header} onClick={() => setChatbotType('main')}>
        {'< 목록으로 돌아가기'}
      </div>
      <div className={styles.msgBox} ref={msgRef}>
        {messages.map((e, i) => (
          <ChatRoomMessage key={i} ico={ico} sender={e.sender} content={e.content} />
        ))}
      </div>
      <form className={styles.form} onSubmit={handleSubmit(onSubmit)} name="messageForm">
        <input type="text" className={styles.input} placeholder="메세지 입력..." {...register('msg')} required />
      </form>
    </div>
  )
}

export default React.memo(ChatRoom)
