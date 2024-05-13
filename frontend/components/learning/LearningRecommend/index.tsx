import React, { useEffect, useState } from 'react'
import styles from './LearningRecommend.module.scss'
import { MdInfoOutline } from 'react-icons/md'
import { useSearchParams } from 'next/navigation'
import LearningRecommendItem from '../LearningRecommendItem'
import { roadmapData } from '@/components/roadmap/main/RoadmapListSection/roadmapData'
import { ILearningCard } from '@/types/learning'
import { getRecommendLearnings } from '@/apis/recommend'
import { useIsLogin } from '@/stores/authStore'
import Loading from '@/components/common/Loading'

const LearningRecommend = () => {
  const [isFetching, setIsFetching] = useState<boolean>(true)
  const [list, setList] = useState<ILearningCard[]>([])
  const params = useSearchParams()
  const keyword = params.get('query') || params.get('oquery') || ''

  const isLogin = useIsLogin()

  const fetchRecommendLearnings = async () => {
    const learnings = await getRecommendLearnings(keyword)
    learnings && isLogin && setList(learnings)
    setIsFetching(false)
  }

  useEffect(() => {
    setIsFetching(true)
    fetchRecommendLearnings()
  }, [params])

  if (!keyword) return
  return (
    <article className={styles.container}>
      <div className={styles.title}>
        <div>우디 Pick!</div>
        <div className={styles.infoWrap}>
          <MdInfoOutline color="#ff9900" cursor="pointer" />
          <p className={styles.arrowBox}>우디는 강의를 추천해주는 클라우디의 서비스입니다</p>
        </div>
      </div>

      {isLogin ? (
        <div className={styles.descLogin}>{`'${keyword}'와 관련된 추천 강의입니다`} </div>
      ) : (
        <div className={styles.desc}>{`로그인 후 ${keyword}와 관련된 강의를 추천받아 보세요!`}</div>
      )}

      {isFetching ? (
        <Loading />
      ) : (
        <div className={styles.wrap}>
          {list.map(item => (
            <LearningRecommendItem key={item.learningId} item={item} />
          ))}
        </div>
      )}
    </article>
  )
}

export default LearningRecommend
