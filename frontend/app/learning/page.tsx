import LearningFilterSection from '@/components/learning/LearningFilterSection'
import LearningSection from '@/components/learning/LearningSection'
import styles from './page.module.scss'

const LearningPage = () => {
  return (
    <>
      <section className={styles.section}>
        <LearningSection />
        <LearningFilterSection />
      </section>
    </>
  )
}

export default LearningPage
