import axios from 'axios'

const url = '/recommend'

const tmpClient = axios.create({
  baseURL: 'https://k10s207.p.ssafy.io:8080/api/v1i',
})

// 강의 추천
export const getRecommendLearnings = async (query: string) => {
  const response = tmpClient
    .get(`${url}/learning?query=${query}&num=4`)
    .then(res => res.data.learningList)
    .catch(err => [])
  return response
}
