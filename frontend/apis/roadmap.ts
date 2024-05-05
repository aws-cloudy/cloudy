import { ROADMAP_ROWS_PER_PAGE } from '@/constants/rows'
import axiosInstance from '@/utils/axiosInstance'

const url = '/roadmaps'

// 로드맵 전체 조회
export const getRoadmaps = async (offset: number, query: string, job: string, service: string) => {
  const response = await axiosInstance
    .get(`${url}?page=${offset}&size=${ROADMAP_ROWS_PER_PAGE}&query=${query}&job=${job}&service=${service}`)
    .then(res => res.data.roadmaps)
    .catch(err => err)
  return response
}
