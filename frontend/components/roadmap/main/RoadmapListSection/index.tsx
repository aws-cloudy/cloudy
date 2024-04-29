import React from 'react'
import RoadmapCard from '@/components/common/RoadmapCard'
import styles from './RoadmapListSection.module.scss'
import { roadmapData } from './roadmapData'

const RoadmapListSection = () => {
  return (
    <div className={styles.container}>
      {roadmapData.map(road => (
        <RoadmapCard item={road} key={road.id} />
      ))}
    </div>
  )
}

export default RoadmapListSection
