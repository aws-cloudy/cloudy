'use client'

import { useSelectedKeywords, useSetSelectedKeywords } from '@/stores/communityStore'
import { dummyKeywrod } from '../dummy'
import styles from './CommunitySidebarKeyword.module.scss'
import React, { useState } from 'react'

function CommunitySidebarKeyword() {
  const [keywords, setKeywords] = useState(dummyKeywrod)
  const selected = useSelectedKeywords()
  const setSelected = useSetSelectedKeywords()

  const addKeyword = (item: string) => {
    setKeywords(prev => prev.filter(e => e !== item))
    setSelected([...selected, item])
  }

  const removeKeyword = (item: string) => {
    setSelected(selected.filter(e => e !== item))
    setKeywords(prev => [...prev, item])
  }

  return (
    <div className={styles.container}>
      <div className={styles.keywordSection}>
        <p className={styles.title}>선택된 키워드</p>
        <div className={styles.keyword}>
          {selected.length === 0 && <div className={styles.keywordNone}>선택된 키워드가 없습니다.</div>}
          {selected.map((e, i) => (
            <span key={i} className={styles.keywordItemC} onClick={() => removeKeyword(e)}>{`#${e}`}</span>
          ))}
        </div>
      </div>
      <div className={styles.keywordSection}>
        <p className={styles.title}>관련 키워드</p>
        <div className={styles.keyword}>
          {keywords.length === 0 && <div className={styles.keywordNone}>키워드가 없습니다.</div>}
          {keywords.map((e, i) => (
            <span key={i} className={styles.keywordItem} onClick={() => addKeyword(e)}>{`#${e}`}</span>
          ))}
        </div>
      </div>
    </div>
  )
}

export default CommunitySidebarKeyword
