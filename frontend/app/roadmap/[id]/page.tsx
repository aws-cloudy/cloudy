import React from 'react'
import Layout from '@/components/common/Layout'
import DetailSection from '@/components/roadmap/detail/DetailSection'
import { getRoadmap } from '@/apis/roadmap'

interface IParams {
  params: { id: number }
}

async function fetchRoadmap(id: number) {
  const response = await getRoadmap(id)
  return response
}

export default async function RaodmapDetailPage({ params: { id } }: IParams) {
  const data = await fetchRoadmap(id)

  return (
    <Layout>
      <DetailSection data={data} />
    </Layout>
  )
}
