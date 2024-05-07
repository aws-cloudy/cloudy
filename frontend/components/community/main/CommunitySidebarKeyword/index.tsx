'use client'

import { useSelectedTags, useCommuSearchActions } from '@/stores/communityStore'
import styles from './CommunitySidebarKeyword.module.scss'
import React, { Suspense, useEffect, useState } from 'react'
import axios from 'axios'
import { IHashtag } from '@/types/community'
import Loading from '@/components/common/Loading'
import { commuURL } from '@/apis/urls'

function CommunitySidebarKeyword() {
  const [keywords, setKeywords] = useState<IHashtag[]>([])
  const [isFetching, setIsFetching] = useState(true)
  const selected = useSelectedTags()
  const { setSelected } = useCommuSearchActions()

  useEffect(() => {
    const hashes = async () => {
      const res = await axios.get(`${commuURL}hashtag`)
      const selectedTitle = selected.map(ea => ea.title)
      setKeywords(
        res.data.hashtags.filter((e: IHashtag) => {
          return selectedTitle.indexOf(e.title) === -1
        }),
      )
      setIsFetching(false)
    }
    hashes()
  }, [])

  const addKeyword = (item: IHashtag) => {
    setKeywords(prev => prev.filter(e => e !== item))
    setSelected([...selected, item])
  }

  const removeKeyword = (item: IHashtag) => {
    setSelected(selected.filter(e => e !== item))
    setKeywords(prev =>
      [...prev, item].toSorted((a, b) => {
        if (a.title > b.title) {
          return 1
        } else if (a.title < b.title) {
          return -1
        } else {
          return 0
        }
      }),
    )
  }

  return (
    <div className={styles.container}>
      <div className={styles.keywordSection}>
        <p className={styles.title}>선택된 키워드</p>
        <div className={styles.keyword}>
          {selected.length === 0 && <div className={styles.keywordNone}>선택된 키워드가 없습니다.</div>}
          {selected.map(e => (
            <span key={e.title} className={styles.keywordItemC} onClick={() => removeKeyword(e)}>{`#${e.title}`}</span>
          ))}
        </div>
      </div>
      <div className={styles.keywordSection}>
        <p className={styles.title}>관련 키워드</p>
        <div className={styles.keyword}>
          {isFetching && <Loading />}
          {keywords.length === 0 && !isFetching && <div className={styles.keywordNone}>키워드가 없습니다.</div>}
          {!isFetching &&
            keywords.map(e => (
              <span key={e.id} className={styles.keywordItem} onClick={() => addKeyword(e)}>{`#${e.title}`}</span>
            ))}
        </div>
      </div>
    </div>
  )
}

export default CommunitySidebarKeyword
