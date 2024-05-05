import LearningFilterSection from '@/components/learning/LearningFilterSection'
import LearningSection from '@/components/learning/LearningSection'
import Layout from '@/components/common/Layout'

export default async function LearningPage() {
  return (
    <>
      <Layout>
        <LearningSection />
        <LearningFilterSection />
      </Layout>
    </>
  )
}
