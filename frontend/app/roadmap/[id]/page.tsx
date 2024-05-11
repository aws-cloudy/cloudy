import React from 'react'
import Layout from '@/components/common/Layout'
import DetailSection from '@/components/roadmap/detail/DetailSection'
import { getRoadmap, getRoadmapComments } from '@/apis/roadmap'
import { getUser } from '@/utils/getUser'
import axios from 'axios'

interface IParams {
  params: { id: number }
}

async function fetchRoadmap(id: number) {
  const response = await getRoadmap(id)
  return response
}

async function fetchRoadmapComments(id: number) {
  const response = await getRoadmapComments(id)
  return response
}

const findIsBookmarked = async (roadmapId: number) => {
  const user = await getUser()
  if (!user?.id) return

  try {
    const bookmarks = await axios
      .get(`https://3m8faj87ji.execute-api.ap-northeast-2.amazonaws.com/prod/api/v1/bookmarks`, {
        params: { memberId: user.id },
        headers: {
          Authorization: `Bearer ${user.accessToken}`,
        },
      })
      .then(res => res.data)

    const roadmapBookmark = bookmarks.roadmaps.find(
      (mark: { roadmapId: number }) => mark.roadmapId === Number(roadmapId),
    )
    return roadmapBookmark.bookmarkId
  } catch (e) {
    console.log(e)
    return null
  }
}

export default async function RaodmapDetailPage({ params: { id } }: IParams) {
  const data = await fetchRoadmap(id)
  const commentsData = await fetchRoadmapComments(id)
  const user = await getUser()
  const bookmarkId = await findIsBookmarked(id)

  return (
    <Layout>
      <DetailSection data={data} comments={commentsData.comments} memberId={user?.id} bookmarkId={bookmarkId} />
    </Layout>
  )
}
