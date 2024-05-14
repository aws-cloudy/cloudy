import { chatClient } from '@/utils/axiosClient'

const url = '/recommend'

// 강의 추천
export const getRecommendLearnings = async (query: string) => {
  const response = chatClient
    .get(`${url}/learning?query=${query}&num=3`)
    .then(res => res.data.learningList)
    .catch(err => err.response.data.code)
  return response
}

// 로드맵 추천
export const getRecommendRoadmaps = async (query: string) => {
  const response = chatClient
    .get(`${url}/roadmap?query=${query}&num=3`)
    .then(res => {
      console.log(res.data)
      return res.data.roadmaps
    })
    .catch(err => err.response.data.code)
  return response
}
