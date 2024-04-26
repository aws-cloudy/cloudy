'use client'

import React, { useState } from 'react'
import styles from './RoadmapFilterSection.module.scss'
import RoadmapFilterDropdown from '../RoadmapFilterDropdown'
import RoadmapFilterInput from '../RoadmapFilterInput'
import { IFilter } from '@/types/learning'
import { roadmapJobFilter, roadmapServiceFilter } from '@/constants/roadmap'

const RoadmapFilterSection = () => {
  const [job, setJob] = useState<IFilter>(roadmapJobFilter[0])
  const [service, setService] = useState<IFilter>(roadmapServiceFilter[0])

  return (
    <section className={styles.section}>
      <RoadmapFilterDropdown job={job} setJob={setJob} service={service} setService={setService} />
      <RoadmapFilterInput />
    </section>
  )
}

export default RoadmapFilterSection
