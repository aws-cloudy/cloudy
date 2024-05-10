import { ROADMAP_ROWS_PER_PAGE } from '@/constants/rows'
import { client } from '@/utils/axiosClient'
import server from '@/utils/axiosServer'

const url = '/roadmaps'

// 로드맵 전체 조회
export const getRoadmaps = async (offset: number, query: string, job: string, service: string) => {
  const response = await client
    .get(`${url}?page=${offset}&size=${ROADMAP_ROWS_PER_PAGE}&query=${query}&job=${job}&service=${service}`)
    .then(res => res.data.roadmaps)
    .catch(err => {
      console.log(err)
    })
  return response
}

// 로드맵 상세 조회
export const getRoadmap = async (id: number) => {
  const response = await server
    .get(`${url}/${id}`)
    .then(res => res.data)
    .catch(err => err)
  return response
}

export const getMainRoadmaps = async (page: number | null) => {
  try {
    const res = await client.get(url, {
      params: {
        page,
        size: 3,
      },
    })
    return res.data.roadmaps
  } catch (e) {
    return null
  }
}

// 로드맵 댓글 전체 조회
export const getRoadmapComments = async (id: number) => {
  const response = await server
    .get(`${url}/${id}/comments`)
    .then(res => {
      console.log('받은 데이터', res.data)
      return res.data
    })
    .catch(err => err)
  return response
}
