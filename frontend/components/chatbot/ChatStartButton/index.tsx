'use client'

import React, { useEffect, useRef, useState } from 'react'
import styles from './ChatStartButton.module.scss'
import { useChatbotActions } from '@/stores/chatbotStore'

function ChatStartButton() {
  const [isBoxShow, setIsBoxShow] = useState(false)
  const { setChatbotType } = useChatbotActions()
  const modalRef = useRef<HTMLDivElement>(null)

  const modalHandler = (e: MouseEvent) => {
    const target = e.target as HTMLElement
    const modal = modalRef.current
    if (!modal) return
    if (!modal.contains(target)) {
      setIsBoxShow(false)
    }
  }

  useEffect(() => {
    window.addEventListener('mousedown', modalHandler)
    return () => window.removeEventListener('mousedown', modalHandler)
  }, [])

  return (
    <div className={styles.container}>
      {isBoxShow && (
        <div className={styles.box} ref={modalRef}>
          <div className={styles.title}>대화 시작</div>
          <div className={styles.boxItem} onClick={() => setChatbotType('cla')}>
            궁금한게 있을 땐? <span>클라와 대화하기</span>
          </div>
          <div className={styles.boxItem} onClick={() => setChatbotType('oudy')}>
            뭐부터 공부하지? <span>우디에게 물어보기</span>
          </div>
        </div>
      )}
      <button className={styles.button} onClick={() => setIsBoxShow(true)}>
        새 대화 시작하기
      </button>
    </div>
  )
}

export default React.memo(ChatStartButton)
