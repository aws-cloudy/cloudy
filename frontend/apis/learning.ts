import { API_URL } from '@/constants/urls'

// 학습 전체 조회 - 검색어 오타 교정 전
export const getLearnings = async (offset: number, limit: number) => {
  const res = await fetch(`${API_URL}/learnings/search?page=${offset}&pageSize=${limit}&type=Exam_Preparation`)
  const data = await res.json().then(response => {
    console.log('data', response)
    return response
  })
  return data.learningList
}
