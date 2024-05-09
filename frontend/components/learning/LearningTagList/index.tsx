import React, { useEffect, useState } from 'react'
import styles from './LearningTagList.module.scss'
import LearningTagListItem from '../LearningTagListItem'
import { useRouter, useSearchParams } from 'next/navigation'
import { IFilter, ILearningFilter } from '@/types/learning'
import { difficultyData, jobData, serviceData, typeData } from '@/constants/learning'
import { extractArrFromQuery, getTextFilter } from '@/utils/getFilterFunc'

const LearningTagList = () => {
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

  const [list, setList] = useState<ILearningFilter>({
    job: jobs,
    service: services,
    type: types,
    difficulty: difficulties,
  })

  const onClick = (item: IFilter) => {
    setList({ ...list, [item.category]: list[item.category].filter(v => v !== item) })
  }

  useEffect(() => {
    setList({ job: jobs, service: services, type: types, difficulty: difficulties })
  }, [params])

  useEffect(() => {
    let url = `/learning?query=${params.get('query') || ''}`
    list.job.length !== 0 && (url += `&job=${getTextFilter(list.job)}`)
    list.service.length !== 0 && (url += `&service=${getTextFilter(list.service)}`)
    list.type.length !== 0 && (url += `&type=${getTextFilter(list.type)}`)
    list.difficulty.length !== 0 && (url += `&difficulty=${getTextFilter(list.difficulty)}`)
    router.push(url)
  }, [list])

  return (
    <div className={styles.container}>
      {list &&
        [...list.job, ...list.service, ...list.type, ...list.difficulty].map(v => (
          <LearningTagListItem key={v.name} item={v} onClick={() => onClick(v)} />
        ))}
    </div>
  )
}

export default LearningTagList
