import { API_URL } from '@/constants/urls'

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
  const res = await fetch(
    `${API_URL}/learnings/search?page=${offset}&pageSize=${limit}&query=${query}&jobName=${job}&serviceName=${service}&type=${type}&difficulty=${difficulty}`,
  )
  console.log('fetch 실행', offset)

  //const data = await res.json()
  const data = { learningList: [] }
  //console.log(query, job, service, type, difficulty)
  // console.log('data', data.learningList)
  return data.learningList
}
