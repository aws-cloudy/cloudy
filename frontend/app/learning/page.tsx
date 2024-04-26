import LearningFilterSection from '@/components/learning/LearningFilterSection'
import LearningSection from '@/components/learning/LearningSection'
import Layout from '@/components/common/Layout'

const LearningPage = () => {
  return (
    <>
      <Layout>
        <LearningSection />
        <LearningFilterSection />
      </Layout>
    </>
  )
}

export default LearningPage
