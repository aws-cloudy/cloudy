'use client'

import { useChatbotActions, useChatbotType } from '@/stores/chatbotStore'
import styles from './ChatRoom.module.scss'
import ChatRoomMessage from '../ChatRoomMessage'
import React, { useEffect, useRef, useState } from 'react'
import ChatRoomInput from '../ChatRoomInput'
import { IMessage } from '@/types/chatbot'
import { chatBotList } from '@/constants/chatbot'

function ChatRoom() {
  const chatBotType = useChatbotType()
  const msgRef = useRef<HTMLDivElement>(null)
  const { sub, name } = chatBotList[chatBotType]
  const { setChatbotType } = useChatbotActions()
  const [messages, setMessages] = useState<IMessage[]>([
    { sender: 'cpu', content: `안녕하세요! ${sub} ${name}입니다. 무엇을 도와드릴까요?` },
  ])
  const [botMessage, setBotMessage] = useState('')
  const [isReceiving, setIsReceiving] = useState(false)

  const handleConnect = async (userMessage: string) => {
    if (userMessage.length === 0) return
    setMessages(prev => [...prev, { sender: 'user', content: userMessage }])
    try {
      const res = await fetch('/cloudy-chat-api', {
        method: 'POST',
        headers: {
          Connection: 'keep-alive',
          'Content-Type': 'application/json; charset=utf-8',
        },
        body: JSON.stringify({ inputData: userMessage }),
      })

      const reader = res.body!.getReader()
      const decoder = new TextDecoder()

      while (true) {
        const { done, value } = await reader.read()
        if (done) {
          setIsReceiving(false)
          break
        }

        if (!value) continue
        const lines = decoder.decode(value)
        const text = lines
          .split('data:')
          .map(each => each.replace('\n\n', ''))
          .join('')
        setBotMessage(prev => prev + text)
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    const messageBox = msgRef.current
    if (!messageBox) return
    messageBox.scrollTo({ top: messageBox.scrollHeight })
  }, [messages, botMessage])

  useEffect(() => {
    if (!isReceiving && botMessage.length > 0) {
      setMessages(prev => [...prev, { sender: 'cpu', content: botMessage }])
      setBotMessage('')
    }
  }, [isReceiving])

  return (
    <div className={styles.container}>
      <div className={styles.header} onClick={() => setChatbotType('main')}>
        {'〈　목록으로 돌아가기'}
      </div>
      <div className={styles.msgBox} ref={msgRef}>
        {messages.map((e, i) => (
          <ChatRoomMessage key={i} sender={e.sender} content={e.content} />
        ))}

        {isReceiving && <ChatRoomMessage sender="cpu" content={botMessage} waiting={botMessage.length === 0} />}
      </div>

      <ChatRoomInput handleConnect={handleConnect} setIsReceiving={setIsReceiving} />
    </div>
  )
}

export default React.memo(ChatRoom)
