'use client'

import React, { useMemo } from 'react'
import styles from './ChatBotIcon.module.scss'

import { RiRobot2Fill } from 'react-icons/ri'
import { useChatbotActions, useIsChatbotOpen } from '@/stores/chatbotStore'
import { usePathname } from 'next/navigation'
import { useIsLogin } from '@/stores/authStore'

function ChatBotIcon() {
  const pathname = usePathname()
  const isChatbotOpen = useIsChatbotOpen()
  const isLogin = useIsLogin()
  const { setIsChatbotOpen } = useChatbotActions()
  const isActive = useMemo(() => {
    if (pathname === '/community/create') return false
    if (pathname.startsWith('/community/update')) return false
    return true
  }, [pathname])

  if (!isLogin) return <></>

  return (
    !isChatbotOpen &&
    isActive && (
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
