'use client'

import { useChatbotActions, useChatbotType } from '@/stores/chatbotStore'
import styles from './ChatRoom.module.scss'
import { LuDog } from 'react-icons/lu'
import { LuCat } from 'react-icons/lu'
import { chatSample } from '../dummy'
import ChatRoomMessage from '../ChatRoomMessage'
import React, { useEffect, useRef, useState } from 'react'
import ChatRoomInput from '../ChatRoomInput'

function ChatRoom() {
  const chatBotType = useChatbotType()
  const msgRef = useRef<HTMLDivElement>(null)
  const { setChatbotType } = useChatbotActions()
  const ico = chatBotType === 'oudy' ? LuDog : LuCat
  const [messages, setMessages] = useState(chatSample)

  useEffect(() => {
    const messageBox = msgRef.current
    if (!messageBox) return
    messageBox.scrollTo({ top: messageBox.scrollHeight })
  }, [messages])

  return (
    <div className={styles.container}>
      <div className={styles.header} onClick={() => setChatbotType('main')}>
        {'〈　목록으로 돌아가기'}
      </div>
      <div className={styles.msgBox} ref={msgRef}>
        {messages.map((e, i) => (
          <ChatRoomMessage key={i} ico={ico} sender={e.sender} content={e.content} />
        ))}
      </div>
      <ChatRoomInput setMessages={setMessages} />
    </div>
  )
}

export default React.memo(ChatRoom)
