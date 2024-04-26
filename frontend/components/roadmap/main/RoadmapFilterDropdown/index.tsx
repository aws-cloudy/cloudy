import React from 'react'
import CustomSelect from '@/components/common/CustomSelect'
import styles from './RoadmapFilterDropdown.module.scss'
import { roadmapJobFilter, roadmapServiceFilter } from '@/constants/roadmap'
import { IRoadmapFilterDropdown } from '@/types/roadmap'

const RoadmapFilterDropdown = (props: IRoadmapFilterDropdown) => {
  const { job, setJob, service, setService } = props

  return (
    <div className={styles.container}>
      <CustomSelect item={job} setItem={setJob} options={roadmapJobFilter} />
      <CustomSelect item={service} setItem={setService} options={roadmapServiceFilter} />
    </div>
  )
}

export default RoadmapFilterDropdown
