'use client'

import React from 'react'
import styles from './ChatBotIcon.module.scss'

import { RiRobot2Fill } from 'react-icons/ri'
import { useChatbotActions, useIsChatbotOpen } from '@/stores/chatbotStore'

function ChatBotIcon() {
  const isChatbotOpen = useIsChatbotOpen()
  const { setIsChatbotOpen } = useChatbotActions()

  return (
    !isChatbotOpen && (
      <div className={styles.container} onClick={() => setIsChatbotOpen(true)}>
        <div className={styles.inner}>
          <RiRobot2Fill className={styles.icon} />
          <div className={styles.messageBox}>
            <div className={styles.messageItem1}>학습 추천과 질문 사항은</div>
            <div className={styles.messageItem2}>챗봇에게 물어보기</div>
          </div>
        </div>
      </div>
    )
  )
}

export default ChatBotIcon
