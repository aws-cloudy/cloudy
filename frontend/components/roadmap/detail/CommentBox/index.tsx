import Comment from './Comment'
import styles from './CommentBox.module.scss'
import React, { useState } from 'react'
import { FaSquareCheck } from "react-icons/fa6";

const CommentBox = ({comments}:any) => {
  const [inputText, setInputText] = useState('');

  const handleInput = (event:any) => {
    // 입력 값 바뀔때마다 상태 업테이트
    setInputText(event.target.value);
  };

  const clickButton = () => {
    console.log(inputText)
  }

  const handleKeyPress = (event:any) => {
    if (event.key === 'Enter') {
      clickButton(); 
      event.preventDefault(); 
    }
  };

  return (
    <>
    
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
      <FaSquareCheck className={styles.icon}/>
      </button>
      </div>
      {comments.length > 0 ? (
        <Comment comments={comments}/>
        
      ) : (
        <div className={styles.notice}>첫번째 댓글을 남겨보세요.</div>
      )}
    
    </>
  )
}

export default CommentBox
