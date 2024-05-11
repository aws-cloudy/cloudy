import { client } from '@/utils/axiosClient'
import server from '@/utils/axiosServer'

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
<<<<<<< 0cfe879d577b2a01a9c103b11e8fe1686e7d1ff0
      // console.log(res.status)
=======
      console.log(res)
>>>>>>> ddce8c2e507a651ddcd4fa8ef5b10055b6d7a514
      return res.data
    })
    .catch(err => err)
  return response
}
