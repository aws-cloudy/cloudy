import React, { useEffect, useRef, useState } from 'react'
import styles from './LearningRecommend.module.scss'
import { MdInfoOutline } from 'react-icons/md'
import { useSearchParams } from 'next/navigation'
import LearningRecommendItem from '../LearningRecommendItem'
import { ILearningCard } from '@/types/learning'
import { getRecommendLearnings } from '@/apis/recommend'
import { useIsLogin } from '@/stores/authStore'
import Loading from '@/components/common/Loading'
import LearningHazard from '../LearningHazardSection'

const LearningRecommend = () => {
  const [isFetching, setIsFetching] = useState<boolean>(false)
  const [list, setList] = useState<ILearningCard[]>([])
  const [isHazard, setIsHazard] = useState<boolean>(false)
  const params = useSearchParams()
  const keyword = params.get('query') || params.get('oquery') || ''

  const isLogin = useIsLogin()

  const fetchRecommendLearnings = async () => {
    const learnings = await getRecommendLearnings(keyword)
    learnings === 'CE008' ? setIsHazard(true) : setList(learnings)
    setIsFetching(false)
  }

  useEffect(() => {
    setIsFetching(true)
    setIsHazard(false)
    keyword && isLogin && fetchRecommendLearnings()
  }, [params])

  if (!keyword) return
  if (!isLogin) return
  if (isHazard) return <LearningHazard />
  return (
    <article className={styles.container}>
      <div className={styles.title}>
        <div>우디 Pick!</div>
        <div className={styles.infoWrap}>
          <MdInfoOutline color="#ff9900" cursor="pointer" />
          <p className={styles.arrowBox}>우디는 강의를 추천해주는 클라우디의 서비스입니다</p>
        </div>
      </div>
      <div className={styles.descLogin}>{`'${keyword}'와 관련된 추천 강의입니다`} </div>
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
