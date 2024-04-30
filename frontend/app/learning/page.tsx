import LearningFilterSection from '@/components/learning/LearningFilterSection'
import LearningSection from '@/components/learning/LearningSection'
import Layout from '@/components/common/Layout'
import { getLearnings } from '@/apis/learning'
import { LEARNING_ROWS_PER_PAGE } from '@/constants/rows'
import { learningData } from '@/components/learning/LearningList/learningData'

export default async function LearningPage() {
  //const initialLearnings = await getLearnings(1, LEARNING_ROWS_PER_PAGE)

  return (
    <>
      <Layout>
        <LearningSection data={learningData} />
        <LearningFilterSection />
      </Layout>
    </>
  )
}
