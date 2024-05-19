import Layout from '@/components/common/Layout'
import PageTitle from '@/components/common/PageTitle'
import RoadmapFilterSection from '@/components/roadmap/main/RoadmapFilterSection'
import RoadmapHazardSection from '@/components/roadmap/main/RoadmapHazardSection'
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
          {/* <RoadmapHazardSection /> */}
          <RoadmapRecommend />
          <RoadmapListSection />
        </Suspense>
      </Layout>
    </>
  )
}

export default RoadmapPage
