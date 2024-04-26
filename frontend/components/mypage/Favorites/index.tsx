'use client'
import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './Favorites.module.scss'
import roadmapgreen from '@/public/img/roadmap/roadmapgreen.jpg'
import roadmapblue from '@/public/img/roadmap/roadmapblue.jpg'

const Favorites = () => {
  //로드맵 더미데이터
  const rData = [
    {
      id: 1,
      image: roadmapgreen,
      status: 'scrap',
      title: 'AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide Guide ',
      context:
        '효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 알 수 있음 효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 보여준다.',
      tags: ['DataWrangler', 'GeneratedAI'],
      comments: 2,
    },
    {
      id: 2,
      image: roadmapblue,
      status: 'scrap',
      title: 'AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide Guide ',
      context:
        '효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 알 수 있음 효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 보여준다.',
      tags: ['DataWrangler', 'GeneratedAI'],
      comments: 2,
    },
    {
      id: 3,
      image: roadmapgreen,
      status: 'scrap',
      title: 'AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide Guide ',
      context:
        '효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 알 수 있음 효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 보여준다.',
      tags: ['DataWrangler', 'GeneratedAI'],
      comments: 2,
    },
  ]

  return (
    <section className={styles.section}>
      <div className={styles.intro}>찜한 로드맵</div>
      <div className={styles.cardContainer}>
        {rData.map((road: any) => (
          <RoadmapCard road={road} key={road.id} />
        ))}
      </div>
    </section>
  )
}

export default Favorites
