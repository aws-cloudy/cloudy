'use client'

import React, { useEffect, useState } from 'react'
import { IFilter, ILearningFilterOpen } from '@/types/learning'
import styles from './LearningFilterOpen.module.scss'
import { MdOutlineClose } from 'react-icons/md'
import { IoIosArrowDown } from 'react-icons/io'
import { IoIosArrowUp } from 'react-icons/io'
import { FiPlus } from 'react-icons/fi'
import { FiMinus } from 'react-icons/fi'
import LearningTagList from '../LearningTagList'
import {
  useDifficultyFilter,
  useLearningActions,
  useServiceFilter,
  useTypeFilter,
  usejobFilter,
} from '@/stores/learning'

const filterData1 = [
  { value: 'Architect', name: 'Architect', category: 'job' },
  { value: 'Buisiness_User', name: 'Buisiness User', category: 'job' },
  { value: 'Cloud_Operator', name: 'Cloud Operator', category: 'job' },
  { value: 'Data_Engineer', name: 'Data Engineer', category: 'job' },
  { value: 'Developer', name: 'Developer', category: 'job' },
  { value: 'Infrastructure_Engineer', name: 'Infrastructure Engineer', category: 'job' },
  { value: 'etc', name: '직무 기타', category: 'job' },
]

const filterData2 = [
  { value: 'Database', name: 'Database', category: 'service' },
  { value: 'Storage', name: 'Storage', category: 'service' },
  { value: 'Machine_Learning', name: 'Machine Learning', category: 'service' },
  { value: 'Cloud_Essentials', name: 'Cloud Essentials', category: 'service' },
  { value: 'Network_&_Content_Delivery', name: 'Network & Content Delivery', category: 'service' },
  { value: 'Serverless', name: 'Serverless', category: 'service' },
  { value: 'etc', name: '서비스 기타', category: 'service' },
]

const filterData3 = [
  { value: 'Digital_Course', name: 'Digital Course', category: 'type' },
  { value: 'Digital_Course_with_Lab', name: 'Digital Course With Lab', category: 'type' },
  { value: 'Exam_Preparation', name: 'Exam Preparation', category: 'type' },
]

const filterData4 = [
  { value: '1', name: '기초', category: 'difficulty' },
  { value: '2', name: '중급', category: 'difficulty' },
  { value: '3', name: '고급', category: 'difficulty' },
]

const LearningFilterOpen = (props: ILearningFilterOpen) => {
  const { closeFilter } = props

  const [filter1, setFilter1] = useState(true)
  const [filter2, setFilter2] = useState(true)
  const [filter3, setFilter3] = useState(true)
  const [filter4, setFilter4] = useState(true)

  const jobs = usejobFilter()
  const services = useServiceFilter()
  const types = useTypeFilter()
  const difficulties = useDifficultyFilter()
  const { setJobFilter, setServiceFilter, setTypeFilter, setDifficultyFilter } = useLearningActions()

  // 있다면 제거, 없다면  추가
  const onClick = (v: IFilter) => {
    switch (v.category) {
      case 'job':
        setJobFilter(v)
        break
      case 'service':
        setServiceFilter(v)
        break
      case 'type':
        setTypeFilter(v)
        break
      case 'difficulty':
        setDifficultyFilter(v)
        break
    }
  }

  useEffect(() => {
    console.log('jobs', jobs)
    console.log('services', services)
    console.log('types', types)
    console.log('dificulties', difficulties)
  }, [jobs, services, types, difficulties])

  return (
    <div className={styles.container}>
      <div onClick={e => closeFilter()} className={styles.closeWrap}>
        <span>close filter</span>
        <MdOutlineClose />
      </div>
      <div className={styles.filterWrap}>
        <div className={styles.wrap}>
          <div className={styles.ItemWrap}>
            <div className={styles.ItemTitle} onClick={e => setFilter1(!filter1)}>
              <span>직무</span>
              {filter1 ? <IoIosArrowUp /> : <IoIosArrowDown />}
            </div>
            {filter1 && (
              <div className={styles.itemList}>
                {filterData1.map(v => (
                  <div className={styles.itemListItem} key={v.value}>
                    <FiPlus onClick={e => onClick(v)} />
                    <span>{v.name}</span>
                  </div>
                ))}
              </div>
            )}
          </div>
          <div className={styles.ItemWrap}>
            <div className={styles.ItemTitle} onClick={e => setFilter2(!filter2)}>
              <span>주요 서비스</span>
              {filter2 ? <IoIosArrowUp /> : <IoIosArrowDown />}
            </div>
            {filter2 && (
              <div className={styles.itemList}>
                {filterData2.map(v => (
                  <div className={styles.itemListItem} key={v.value}>
                    <FiPlus onClick={e => onClick(v)} />
                    <span>{v.name}</span>
                  </div>
                ))}
              </div>
            )}
          </div>
          <div className={styles.ItemWrap}>
            <div className={styles.ItemTitle} onClick={e => setFilter3(!filter3)}>
              <span>분류</span>
              {filter3 ? <IoIosArrowUp /> : <IoIosArrowDown />}
            </div>
            {filter3 && (
              <div className={styles.itemList}>
                {filterData3.map(v => (
                  <div className={styles.itemListItem} key={v.value}>
                    <FiPlus onClick={e => onClick(v)} />
                    <span>{v.name}</span>
                  </div>
                ))}
              </div>
            )}
          </div>
          <div className={styles.ItemWrap}>
            <div className={styles.ItemTitle} onClick={e => setFilter4(!filter4)}>
              <span>난이도</span>
              {filter4 ? <IoIosArrowUp /> : <IoIosArrowDown />}
            </div>
            {filter4 && (
              <div className={styles.itemList}>
                {filterData4.map(v => (
                  <div className={styles.itemListItem} key={v.value}>
                    <FiPlus onClick={e => onClick(v)} />
                    <span>{v.name}</span>
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
        <LearningTagList />
      </div>
    </div>
  )
}

export default LearningFilterOpen
