import { client } from '@/utils/axiosClient'
import server from '@/utils/axiosServer'
import { getSession } from 'next-auth/react'

const url = '/bookmarks'

// 찜한 로드맵 전체 조회
export const getBookmarks = async (memberId: string) => {
  const response = await client
    .get(url, { params: { memberId } })
    .then(res => {
      console.log(res)
      return res.data
    })
    .catch(err => err)
  console.log('받음', response)
  return response
}

// 북마크 생성
// export const postBookmark = async (roadmapId: number) => {
//   console.log('보내는 번호', roadmapId)
//   try {
//     const session = await getSession()
//     const token = session?.accessToken
//     if (!token) {
//       throw new Error('No access token available')
//     }

//     const response = await server.post(
//       url,
//       { roadmapId },
//       {
//         headers: {
//           Authorization: `Bearer ${token}`,
//         },
//       },
//     )
//     console.log('북마크 생성 성공', response.data)
//     return response.data
//   } catch (error) {
//     console.error('북마크 생성 실패', error)
//     throw error
//   }
// }

// 회원 로드맵 북마크 생성
export const postBookmark = async (roadmapId: number) => {
  console.log('북마크아이디', roadmapId)
  const response = await client
    .post(url, { roadmapId: roadmapId })
    .then(res => {
      console.log(res)
      return res.data
    })
    .catch(err => {
      console.error(err)
    })
  console.log(response)
  return response
}

// 회원 로드맵 북마크 해제
export const deleteBookmark = async (roadmapId: number) => {
  const bookmarkId = roadmapId
  const response = await client
    .delete(`${url}/${bookmarkId}`)
    .then(res => res.data)
    .catch(err => err)
  return response
}

// // 회원 로드맵 북마크 해제
// export const deleteBookmark = async (roadmapId: number) => {
//   const session = await getSession()
//   const token = session?.accessToken
//   const bookmarkId = roadmapId
//   const response = await server
//     .delete(`${url}/${bookmarkId}`, { headers: { Authorization: `Bearer ${token}` } })
//     .then(res => res.data)
//     .catch(err => err)
//   return response
// }
