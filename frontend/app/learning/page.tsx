import LearningFilterSection from '@/components/learning/LearningFilterSection'
import LearningSection from '@/components/learning/LearningSection'
import Layout from '@/components/common/Layout'
import { getLearnings } from '@/apis/learning'

export default async function LearningPage() {
  const initialLearnings = await getLearnings(1, 12)

  return (
    <>
      <Layout>
        <LearningSection data={initialLearnings} />
        <LearningFilterSection />
      </Layout>
    </>
  )
}
