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
  const [isLast, setIsLast] = useState(false)
  const lastId = useRef(0)
  const selected = useSelectedTags()
  const keyword = useCommuSearchKeyword()

  const getPosts = async () => {
    console.log('get posts')
    if (isLast) return

    const res = await axios.get(`${commuURL}/question`, {
      params: {
        searchword: keyword.length > 0 ? keyword : null,
        tag: selected.length > 0 ? selected.map(e => `#${e.title}`).join('') : null,
        lastId: lastId.current,
      },
    })

    if (res.data.isLast) {
      setIsLast(true)
    } else {
      const len = res.data.questionList.length - 1
      lastId.current = res.data.questionList[len].id
      setQuestions(prev => [...prev, ...res.data.questionList])
    }
    setIsFetching(false)
  }

  useEffect(() => {
    setIsLast(false)
    setQuestions([])
    lastId.current = 0
    getPosts()
  }, [selected, keyword])

  const observerCallback: IntersectionObserverCallback = ([{ isIntersecting }]) => {
    if (isIntersecting) {
      getPosts()
    }
  }

  return (
    <div className={styles.container}>
      {isFetching ? (
        <Loading />
      ) : (
        <>
          <p className={styles.result}>총 {questions.length}개의 질문이 등록되어 있습니다.</p>
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
