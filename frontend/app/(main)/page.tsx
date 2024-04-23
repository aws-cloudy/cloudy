import MainHeader from '@/components/main/MainHeader'
import styles from '../page.module.scss'
import MainIntroduce from '@/components/main/MainIntroduce'
import MainLectureRecom from '@/components/main/MainLectureRecom'
import MainRoadmapRecom from '@/components/main/MainRoadmapRecom'
import MainFooter from '@/components/main/MainFooter'

export default function Home() {
  return (
    <section className={styles.section}>
      <div>
        <MainHeader />
        <MainIntroduce />
        <MainLectureRecom />
        <MainRoadmapRecom />
        <MainFooter />
      </div>
    </section>
  )
}
