'use client'

import React, { useEffect, useState } from 'react'
import { IFilter, ILearningFilter, ILearningFilterOpen } from '@/types/learning'
import styles from './LearningFilterOpen.module.scss'
import { MdOutlineClose } from 'react-icons/md'
import LearningTagList from '../LearningTagList'
import LearningFilterToggle from '../LearningFilterToggle'
import { difficultyData, jobData, serviceData, typeData } from '@/constants/learning'
import { useRouter, useSearchParams } from 'next/navigation'
import { extractArrFromQuery, getTextFilter } from '@/utils/getFilterFunc'

const LearningFilterOpen = (props: ILearningFilterOpen) => {
  const { closeFilter } = props

  const router = useRouter()
  const params = useSearchParams()

  const job = params.get('job')
  const service = params.get('service')
  const type = params.get('type')
  const difficulty = params.get('difficulty')

  const jobs = extractArrFromQuery(job, jobData)
  const services = extractArrFromQuery(service, serviceData)
  const types = extractArrFromQuery(type, typeData)
  const difficulties = extractArrFromQuery(difficulty, difficultyData)

  const [filter, setFilter] = useState<ILearningFilter>({
    job: jobs,
    service: services,
    type: types,
    difficulty: difficulties,
  })

  useEffect(() => {
    let url = `/learning?query=${params.get('query') || ''}`
    filter.job.length !== 0 && (url += `&job=${getTextFilter(filter.job)}`)
    filter.service.length !== 0 && (url += `&service=${getTextFilter(filter.service)}`)
    filter.type.length !== 0 && (url += `&type=${getTextFilter(filter.type)}`)
    filter.difficulty.length !== 0 && (url += `&difficulty=${getTextFilter(filter.difficulty)}`)
    router.push(url)
  }, [filter])

  return (
    <div className={styles.container}>
      <div onClick={e => closeFilter()} className={styles.closeWrap}>
        <span>close filter</span>
        <MdOutlineClose />
      </div>
      <div className={styles.filterWrap}>
        <LearningFilterToggle
          title="직무"
          data={jobData}
          setFilter={(v: IFilter[]) => setFilter({ ...filter, job: v })}
        />
        <LearningFilterToggle
          title="주요 서비스"
          data={serviceData}
          setFilter={(v: IFilter[]) => setFilter({ ...filter, service: v })}
        />
        <LearningFilterToggle
          title="분류"
          data={typeData}
          setFilter={(v: IFilter[]) => setFilter({ ...filter, type: v })}
        />
        <LearningFilterToggle
          title="난이도"
          data={difficultyData}
          setFilter={(v: IFilter[]) => setFilter({ ...filter, difficulty: v })}
        />
        <LearningTagList />
      </div>
    </div>
  )
}

export default LearningFilterOpen
