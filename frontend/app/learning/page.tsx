import LearningFilterSection from '@/components/learning/LearningFilterSection'
import LearningSection from '@/components/learning/LearningSection'
import Layout from '@/components/common/Layout'
import { Suspense } from 'react'

export default async function LearningPage() {
  return (
    <>
      <Layout>
        <Suspense>
          <LearningSection />
          <LearningFilterSection />
        </Suspense>
      </Layout>
    </>
  )
}
