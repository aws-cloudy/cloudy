import { fetchLearningRecom } from '@/apis/mainLearing'
import { getUser } from '@/utils/getUser'
import React from 'react'

async function MainLectureRecomItems() {
  const user = await getUser()
  // const learningList = await fetchLearningRecom(1)

  return <div>MainLectureRecomItems</div>
}

export default MainLectureRecomItems
