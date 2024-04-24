'use client'

import { useChatbotActions, useChatbotType, useIsChatbotOpen } from '@/stores/chatbotStore'
import styles from './ChatBot.module.scss'

import { FaChevronDown } from 'react-icons/fa'
import ChatList from '../ChatList'
import ChatStartButton from '../ChatStartButton'
import { chatbotHeader } from '../dummy'
import ChatRoom from '../ChatRoom'

function ChatBot() {
  const isChatbotOpen = useIsChatbotOpen()
  const chatBotType = useChatbotType()
  const { setIsChatbotOpen } = useChatbotActions()

  return (
    isChatbotOpen && (
      <div className={styles.container}>
        <div className={styles.inner}>
          <div className={styles.header}>
            <div>
              <div className={styles.title}>{chatbotHeader[chatBotType].title}</div>
              <div className={styles.desc}>{chatbotHeader[chatBotType].msg}</div>
            </div>
            <FaChevronDown className={styles.icon} onClick={() => setIsChatbotOpen(false)} />
          </div>
          <div className={styles.content}>
            {chatBotType === 'main' && (
              <>
                <ChatList />
                <ChatStartButton />
              </>
            )}
            {(chatBotType === 'cla' || chatBotType === 'oudy') && <ChatRoom />}
          </div>
        </div>
      </div>
    )
  )
}

export default ChatBot
