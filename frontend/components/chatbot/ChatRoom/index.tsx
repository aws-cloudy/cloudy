'use client'

import { useChatbotActions, useChatbotType } from '@/stores/chatbotStore'
import styles from './ChatRoom.module.scss'
import ChatRoomMessage from '../ChatRoomMessage'
import React, { useEffect, useRef, useState } from 'react'
import ChatRoomInput from '../ChatRoomInput'
import { IMessage } from '@/types/chatbot'
import { chatBotList } from '@/constants/chatbot'
import { getSession } from 'next-auth/react'
import ChatRoomPrevMsg from '../ChatRoomPrevMsg'

function ChatRoom() {
  const chatBotType = useChatbotType()
  const msgRef = useRef<HTMLDivElement>(null)
  const { setChatbotType } = useChatbotActions()
  const [prevMessages, setPrevMessages] = useState<IMessage[]>([])
  const [messages, setMessages] = useState<IMessage[]>([])
  const [botMessage, setBotMessage] = useState('')
  const [isReceiving, setIsReceiving] = useState(false)
  const [isInitialFetching, setIsInitialFetching] = useState(true)
  const prevHeight = useRef(0)

  const handleConnect = async (userMessage: string) => {
    if (userMessage.length === 0) return
    const session = await getSession()
    if (!session?.accessToken) return
    const token = session.accessToken
    const type = chatBotList[chatBotType].type
    setMessages(prev => [...prev, { isUserSent: true, content: userMessage, regAt: new Date().toDateString() }])
    try {
      const res = await fetch(`${process.env.NEXT_PUBLIC_CHAT_SERVER_URL}?type=${type}`, {
        method: 'POST',
        headers: {
          Connection: 'keep-alive',
          'Content-Type': 'application/json; charset=utf-8',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ type, inputData: userMessage }),
      })

      if (res.status === 400) {
        setBotMessage('사용자의 질문이 부적절한 컨텐츠를 담고 있습니다.')
        setIsReceiving(false)
        return
      }

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
    prevHeight.current = messageBox.scrollHeight
    messageBox.scrollTo({ top: messageBox.scrollHeight })
  }, [messages, botMessage, isInitialFetching])

  useEffect(() => {
    if (isInitialFetching) {
      setIsInitialFetching(false)
    } else {
      const messageBox = msgRef.current
      if (!messageBox) return
      messageBox.scrollTo({ top: messageBox.scrollHeight - prevHeight.current })
      prevHeight.current = messageBox.scrollHeight
    }
  }, [prevMessages])

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
        <ChatRoomPrevMsg
          prevMessages={prevMessages}
          setPrevMessages={setPrevMessages}
          isInitialFetching={isInitialFetching}
          setIsInitialFetching={setIsInitialFetching}
        />
        {messages.map((e, i) => (
          <ChatRoomMessage key={i} isUserSent={e.isUserSent} content={e.content} regAt={e.regAt} />
        ))}
        {isReceiving && (
          <ChatRoomMessage isUserSent={false} content={botMessage} waiting={botMessage.length === 0} regAt={null} />
        )}
      </div>

      <ChatRoomInput handleConnect={handleConnect} setIsReceiving={setIsReceiving} isReceiving={isReceiving} />
    </div>
  )
}

export default React.memo(ChatRoom)
