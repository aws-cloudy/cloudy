import { client } from '@/utils/axiosClient'

const url = '/comments'

// 로드맵 댓글 생성
export const postRoadmapComment = async (roadmapId: number, memberId: string, content: string) => {
  const response = client
    .post(`${url}/roadmaps`, { roadmapId, memberId, content })
    .then(res => res.data)
    .catch(err => err)
  return response
}

// 로드맵 댓글 삭제
export const deleteRoadmapComment = async (roadmapId: number, commentId: number) => {
  const response = client
    .delete(`${url}/${commentId}/roadmaps`)
    .then(res => res.data)
    .catch(err => err)
  return response
}
