import React from 'react'
import Layout from '@/components/common/Layout'
import DetailSection from '@/components/roadmap/detail/DetailSection'
import { getRoadmap, getRoadmapComments } from '@/apis/roadmap'
import { getSession } from 'next-auth/react'
import { getBookmarks } from '@/apis/bookmark'

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

export default async function RaodmapDetailPage({ params: { id } }: IParams) {
  const data = await fetchRoadmap(id)
  const commentsData = await fetchRoadmapComments(id)

  return (
    <Layout>
      <DetailSection data={data} comments={commentsData.comments} />
    </Layout>
  )
}
