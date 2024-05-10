'use client'

import styles from './CommentSection.module.scss'
import React, { useEffect, useState } from 'react'
import { FaSquareCheck } from 'react-icons/fa6'
import CommentItem from '../CommentItem'
import { IRoadmapDetailData } from '@/types/roadmap'

const CommentSection = ({ comments }: IRoadmapDetailData) => {
  const [inputText, setInputText] = useState('')

  const handleInput = (event: any) => {
    // 입력 값 바뀔때마다 상태 업테이트
    setInputText(event.target.value)
  }

  const addComment = async () => {
    console.log(inputText)
  }

  const handleKeyPress = (event: any) => {
    if (event.key === 'Enter') {
      addComment()
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
        <button onClick={addComment} className={styles.iconBox}>
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
