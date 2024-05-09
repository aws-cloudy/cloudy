import { ILearningCard } from '@/types/learning'
import { client, searchClient } from '@/utils/axiosClient'

const url = '/learnings'

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
  // const response = client
  //   .get(
  //     `${url}/search?page=${offset}&pageSize=${limit}&query=${query}&jobName=${job}&serviceName=${service}&type=${type}&difficulty=${difficulty}`,
  //   )
  //   .then(res => res.data.learningList)
  //   .catch(err => err)
  const response: ILearningCard[] = []
  return response
}

// 학습 검색어 자동 완성 조회
export const getSearchAutoComplete = async (query: string) => {
  const response = searchClient
    .post(`${url}/search/autocomplete`, { query: query })
    .then(res => res.data.searchList)
    .catch(err => err)
  return response
}
