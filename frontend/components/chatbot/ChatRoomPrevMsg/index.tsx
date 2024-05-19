import React, { useEffect, useRef, useState } from 'react'
import axios from 'axios'
import { chatBotList } from '@/constants/chatbot'
import { useToken } from '@/stores/authStore'
import { useChatbotType } from '@/stores/chatbotStore'
import { IChatRoomPrevMsg, IMessage } from '@/types/chatbot'
import ChatRoomMessage from '../ChatRoomMessage'
import Observer from '@/components/common/Observer'
import styles from './ChatRoomPrevMsg.module.scss'
import { chatClient } from '@/utils/axiosClient'

function ChatRoomPrevMsg({ prevMessages, setPrevMessages, isInitialFetching, setIsInitialFetching }: IChatRoomPrevMsg) {
  const [prevMsgAll, setPrevMsgAll] = useState<IMessage[]>([])
  const len = useRef(0)
  const chatBotType = useChatbotType()
  const accessToken = useToken()
  const [addMore, setAddMore] = useState(false)

  const getPreviousChat = async () => {
    const type = chatBotList[chatBotType].type
    const res = await chatClient.get(`/chats?type=${type}`, {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    })
    const chat = res.data.chatList.reverse()
    len.current = chat.length - 5 > 0 ? chat.length - 5 : 0
    setPrevMsgAll(chat)
    setPrevMessages(chat.slice(len.current, chat.length))
  }

  useEffect(() => {
    getPreviousChat()
  }, [])

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (!isInitialFetching && isIntersecting && len.current > 0) {
      setAddMore(true)
    }
  }
  useEffect(() => {
    if (addMore) {
      const temp = len.current - 5 > 0 ? len.current - 5 : 0
      setPrevMessages(prev => [...prevMsgAll.slice(temp, len.current), ...prev])
      len.current = temp
      setAddMore(false)
    }
  }, [addMore])

  return (
    <>
      {len.current > 0 && <Observer callback={observerCallback} />}
      {prevMessages.map((e, i) => (
        <ChatRoomMessage key={i} content={e.content} isUserSent={e.isUserSent} regAt={e.regAt} />
      ))}
      <div className={styles.newMessage}>
        <p>질문을 입력하여 대화를 시작해보세요</p>
      </div>
    </>
  )
}

export default ChatRoomPrevMsg
