import { client } from '@/utils/axiosClient'

const url = '/bookmarks'

// 찜한 로드맵 전체 조회
export const getBookmarks = async (memberId: string) => {
  const response = await client.get(url, { params: { memberId } })
  console.log(response.data)
  return response.data
}

// 회원 로드맵 북마크 생성
export const postBookmark = async (roadmapId: number) => {
  const response = await client
    .post(url, { roadmapId: roadmapId })
    .then(res => res.data)
    .catch(err => {
      console.error(err)
    })
  console.log(response)
  return response
}

// 회원 로드맵 북마크 해제
export const deleteBookmark = async (bookmarkId: number) => {
  const response = await client
    .delete(`${url}/${bookmarkId}`)
    .then(res => {
      console.log(res)
      return res.data
    })
    .catch(err => err)
  return response
}
