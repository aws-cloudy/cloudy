import { useEffect, useState } from 'react'
import axios from 'axios'
import styles from './CommunityList.module.scss'

import CommunityListItem from '../CommunityListItem'
import { useCommuSearchKeyword, useSelectedTags } from '@/stores/communityStore'
import { ICommunityListItem } from '@/types/community'
import { commuURL } from '@/apis/urls'
import Loading from '@/components/common/Loading'

function CommunityList() {
  const [questions, setQuestions] = useState<ICommunityListItem[]>([])
  const [isFetching, setIsFetching] = useState(true)
  const selected = useSelectedTags()
  const keyword = useCommuSearchKeyword()

  useEffect(() => {
    const getPosts = async () => {
      const res = await axios.get(`${commuURL}/question`, {
        params: {
          searchword: keyword.length > 0 ? keyword : null,
          tag: selected.length > 0 ? selected.map(e => `#${e.title}`).join('') : null,
        },
      })
      setQuestions(res.data.questionList)
      setIsFetching(false)
    }
    getPosts()
  }, [selected, keyword])

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
        </>
      )}
    </div>
  )
}

export default CommunityList
