import React from 'react'
import roadmapgreen from '@/public/img/roadmap/roadmapgreen.jpg'
import roadmapblue from '@/public/img/roadmap/roadmapblue.jpg'
import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './RoadmapListSection.module.scss'

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
  {
    id: 4,
    image: roadmapgreen,
    status: 'scrap',
    title: 'AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide Guide ',
    context:
      '효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 알 수 있음 효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 보여준다.',
    tags: ['DataWrangler', 'GeneratedAI'],
    comments: 2,
  },
]

const RoadmapListSection = () => {
  return (
    <div className={styles.container}>
      {rData.map((road: any) => (
        <RoadmapCard road={road} key={road.id} />
      ))}
    </div>
  )
}

export default RoadmapListSection
