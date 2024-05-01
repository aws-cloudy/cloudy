import { API_URL } from '@/constants/urls'
import { IFilter } from '@/types/learning'

// 학습 전체 조회 - 검색어 오타 교정 전
export const getLearnings = async (
  offset: number,
  limit: number,
  query: string,
  job: string,
  service: string,
  type: string,
  difficulty: string,
) => {
  // const res = await fetch(`${API_URL}/learnings/search?page=${offset}&pageSize=${limit}&type=Exam_Preparation`)
  // console.log('검색어', query)
  // console.log('직무', job)
  // console.log('주요 서비스', service)
  // console.log('분류', type)
  // console.log('난이도', difficulty)

  const res = await fetch('http://localhost:3000/api/learning')
  const data = await res.json().then(response => {
    // console.log('data', response)
    return response
  })
  return data.learningList
}
