import Layout from '@/components/common/Layout'
import PageTitle from '@/components/common/PageTitle'
import RoadmapFilterSection from '@/components/roadmap/main/RoadmapFilterSection'
import RoadmapListSection from '@/components/roadmap/main/RoadmapListSection'
import RoadmapRecommend from '@/components/roadmap/main/RoadmapRecommend'
import { Suspense } from 'react'

const RoadmapPage = () => {
  return (
    <>
      <Layout>
        <PageTitle />
        <Suspense>
          <RoadmapFilterSection />
          <RoadmapRecommend />
          <RoadmapListSection />
        </Suspense>
      </Layout>
    </>
  )
}

export default RoadmapPage
