'use client'

import { useChatbotActions, useChatbotType } from '@/stores/chatbotStore'
import styles from './ChatRoom.module.scss'
import ChatRoomMessage from '../ChatRoomMessage'
import React, { useEffect, useRef, useState } from 'react'
import ChatRoomInput from '../ChatRoomInput'
import { IMessage } from '@/types/chatbot'
import { chatBotList } from '@/constants/chatbot'
import { getSession } from 'next-auth/react'

function ChatRoom() {
  const chatBotType = useChatbotType()
  const msgRef = useRef<HTMLDivElement>(null)
  const { sub, name } = chatBotList[chatBotType]
  const { setChatbotType } = useChatbotActions()
  const [messages, setMessages] = useState<IMessage[]>([
    {
      isUserSent: false,
      content: `안녕하세요! ${sub} ${name}입니다. 무엇을 도와드릴까요?`,
      regAt: new Date().toDateString(),
    },
  ])
  const [botMessage, setBotMessage] = useState('')
  const [isReceiving, setIsReceiving] = useState(false)

  const handleConnect = async (userMessage: string) => {
    if (userMessage.length === 0) return
    const session = await getSession()
    if (!session?.user) return
    const userId = session.user.id
    const type = chatBotList[chatBotType].type
    setMessages(prev => [...prev, { isUserSent: true, content: userMessage, regAt: new Date().toDateString() }])
    try {
      const res = await fetch(`/cloudy-chat-api?type=${type}`, {
        method: 'POST',
        headers: {
          Connection: 'keep-alive',
          'Content-Type': 'application/json; charset=utf-8',
          'User-Id': userId!,
        },
        body: JSON.stringify({ type, inputData: userMessage }),
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
      setMessages(prev => [...prev, { isUserSent: false, content: botMessage, regAt: null }])
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
          <ChatRoomMessage key={i} isUserSent={e.isUserSent} content={e.content} regAt={e.regAt} />
        ))}

        {isReceiving && (
          <ChatRoomMessage isUserSent={false} content={botMessage} waiting={botMessage.length === 0} regAt={null} />
        )}
      </div>

      <ChatRoomInput handleConnect={handleConnect} setIsReceiving={setIsReceiving} />
    </div>
  )
}

export default React.memo(ChatRoom)
