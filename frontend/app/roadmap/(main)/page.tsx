import Layout from '@/components/common/Layout'
import PageTitle from '@/components/common/PageTitle'
import RoadmapFilterSection from '@/components/roadmap/main/RoadmapFilterSection'
import RoadmapListSection from '@/components/roadmap/main/RoadmapListSection'

const RoadmapPage = () => {
  return (
    <>
      <Layout>
        <PageTitle />
        <RoadmapFilterSection />
        <RoadmapListSection />
      </Layout>
    </>
  )
}

export default RoadmapPage
