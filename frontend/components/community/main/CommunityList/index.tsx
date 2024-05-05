import { useEffect, useRef, useState } from 'react'
import axios from 'axios'
import styles from './CommunityList.module.scss'

import CommunityListItem from '../CommunityListItem'
import { useCommuSearchKeyword, useSelectedTags } from '@/stores/communityStore'
import { ICommunityListItem } from '@/types/community'
import { commuURL } from '@/apis/urls'
import Loading from '@/components/common/Loading'
import Observer from '../../../common/Observer'

function CommunityList() {
  const [questions, setQuestions] = useState<ICommunityListItem[]>([])
  const [isFetching, setIsFetching] = useState(true)
  const [isAdding, setIsAdding] = useState(false)
  const [count, setCount] = useState(0)
  const isLast = useRef(false)
  const lastId = useRef(0)
  const selected = useSelectedTags()
  const keyword = useCommuSearchKeyword()

  const getPosts = async () => {
    console.log('get posts')
    if (isLast.current) return

    try {
      const res = await axios.get(`${commuURL}/question`, {
        params: {
          searchword: keyword.length > 0 ? keyword : null,
          tag: selected.length > 0 ? selected.map(e => `#${e.title}`).join('') : null,
          lastId: lastId.current,
        },
      })
      setCount(res.data.count)
      if (res.data.isLast) {
        isLast.current = true
      } else {
        const len = res.data.questionList.length - 1
        lastId.current = res.data.questionList[len].id
        setQuestions(prev => [...prev, ...res.data.questionList])
      }
    } catch (e) {
      console.log(e)
    }
    setIsFetching(false)
    setIsAdding(false)
  }

  useEffect(() => {
    window.scrollTo({ top: 0 })
    setIsFetching(true)
    setQuestions([])
    isLast.current = false
    lastId.current = 0
    getPosts()
  }, [selected, keyword])

  useEffect(() => {
    if (!isAdding) return
    getPosts()
  }, [isAdding])

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isAdding) return
    if (isIntersecting) {
      setIsAdding(true)
    }
  }

  return (
    <div className={styles.container}>
      {isFetching ? (
        <Loading />
      ) : (
        <>
          <p className={styles.result}>총 {count}개의 질문이 등록되어 있습니다.</p>
          {questions.map(e => (
            <CommunityListItem key={e.id} question={e} />
          ))}
          <Observer callback={observerCallback} />
        </>
      )}
    </div>
  )
}

export default CommunityList
