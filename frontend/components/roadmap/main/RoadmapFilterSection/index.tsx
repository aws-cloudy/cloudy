'use client'

import React, { useEffect, useState } from 'react'
import styles from './RoadmapFilterSection.module.scss'
import RoadmapFilterDropdown from '../RoadmapFilterDropdown'
import RoadmapFilterInput from '../RoadmapFilterInput'
import { IFilter } from '@/types/learning'
import { roadmapJobFilter, roadmapServiceFilter } from '@/constants/roadmap'
import { useRouter, useSearchParams } from 'next/navigation'
import { extractArrFromQuery } from '@/utils/getFilterFunc'

const RoadmapFilterSection = () => {
  const router = useRouter()
  const params = useSearchParams()

  const [job, setJob] = useState<IFilter>(
    extractArrFromQuery(params.get('job'), roadmapJobFilter)[0] || roadmapJobFilter[0],
  )
  const [service, setService] = useState<IFilter>(
    extractArrFromQuery(params.get('service'), roadmapServiceFilter)[0] || roadmapServiceFilter[0],
  )
  const [keyword, setKeyword] = useState<string>('')

  const onSearch = () => {
    let url = '/roadmap'
    keyword && (url += `?query=${keyword}`)
    job.value && (url += `${keyword ? '&' : '?'}job=${job.value}`)
    service.value && (url += `${keyword || job.value ? '&' : '?'}service=${service.value}`)
    router.push(url)
  }

  useEffect(() => {
    setJob(extractArrFromQuery(params.get('job'), roadmapJobFilter)[0] || roadmapJobFilter[0])
    setService(extractArrFromQuery(params.get('service'), roadmapServiceFilter)[0] || roadmapServiceFilter[0])
  }, [params])

  return (
    <section className={styles.section}>
      <RoadmapFilterDropdown job={job} setJob={setJob} service={service} setService={setService} />
      <RoadmapFilterInput value={keyword} setValue={setKeyword} onSearch={onSearch} />
    </section>
  )
}

export default RoadmapFilterSection
