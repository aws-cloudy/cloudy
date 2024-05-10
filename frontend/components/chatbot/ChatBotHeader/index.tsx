import React from 'react'
import styles from './ChatBotHeader.module.scss'
import { chatBotList } from '@/constants/chatbot'
import { FaChevronDown } from 'react-icons/fa'
import { useChatbotActions, useChatbotType } from '@/stores/chatbotStore'

function ChatBotHeader() {
  const chatBotType = useChatbotType()
  const { setIsChatbotOpen } = useChatbotActions()

  return (
    <div className={styles.container}>
      <div>
        <div className={styles.title}>{chatBotList[chatBotType].name}</div>
        <div className={styles.desc}>{chatBotList[chatBotType].msg}</div>
      </div>
      <FaChevronDown className={styles.icon} onClick={() => setIsChatbotOpen(false)} />
    </div>
  )
}

export default ChatBotHeader
