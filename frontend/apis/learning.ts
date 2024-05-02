import axiosInstance from '@/utils/axiosInstance'

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
  const res = axiosInstance
    .get(
      `/learnings/search?page=${offset}&pageSize=${limit}&query=${query}&jobName=${job}&serviceName=${service}&type=${type}&difficulty=${difficulty}`,
    )
    .then(res => res.data.learningList)
    .catch(err => err)

  return res
}
