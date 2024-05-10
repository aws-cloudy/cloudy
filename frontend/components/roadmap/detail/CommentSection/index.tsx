'use client'

import styles from './CommentSection.module.scss'
import React, { useEffect, useState } from 'react'
import { FaSquareCheck } from 'react-icons/fa6'
import CommentItem from '../CommentItem'
import { IRoadmapDetailData } from '@/types/roadmap'

const CommentSection = ({ comments }: IRoadmapDetailData) => {
  // const comments = [
  //   {
  //     id: 1,
  //     name: '김싸피',
  //     date: '2024.04.15. 11:53',
  //     context: '이거대로 공부하고 자격증 합격했어요. 강추합니다 !!',
  //   },
  //   {
  //     id: 2,
  //     name: '이싸피',
  //     date: '2024.04.14. 11:53',
  //     context:
  //       '정말 좋은 로드맵인 것 같아요 ㅎㅎ 어떻게 시작해야할지 고민되었었는데 ㅎㅎㅎ 다른 분들도 꼭 합격하시길!!',
  //   },
  // ]

  const [inputText, setInputText] = useState('')

  const handleInput = (event: any) => {
    // 입력 값 바뀔때마다 상태 업테이트
    setInputText(event.target.value)
  }

  const clickButton = () => {
    console.log(inputText)
  }

  const handleKeyPress = (event: any) => {
    if (event.key === 'Enter') {
      clickButton()
      event.preventDefault()
    }
  }

  return (
    <div className={styles.section}>
      <div className={styles.counts}>댓글 {comments.length}개</div>
      <div className={styles.inputContainer}>
        <input
          type="text"
          placeholder="댓글 달기..."
          className={styles.inputBox}
          value={inputText}
          onChange={handleInput}
          onKeyPress={handleKeyPress}
        />
        <button onClick={clickButton} className={styles.iconBox}>
          <FaSquareCheck className={styles.icon} />
        </button>
      </div>
      {comments.length > 0 ? (
        <CommentItem comments={comments} />
      ) : (
        <div className={styles.notice}>첫번째 댓글을 남겨보세요.</div>
      )}
    </div>
  )
}

export default CommentSection
