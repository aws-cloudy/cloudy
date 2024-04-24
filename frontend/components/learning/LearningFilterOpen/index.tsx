'use client'

import React, { useState } from 'react'
import { IFilter, ILearningFilterOpen } from '@/types/learning'
import styles from './LearningFilterOpen.module.scss'
import { MdOutlineClose } from 'react-icons/md'
import { IoIosArrowDown } from 'react-icons/io'
import { IoIosArrowUp } from 'react-icons/io'
import { FiPlus } from 'react-icons/fi'
import { FiMinus } from 'react-icons/fi'
import LearningTagList from '../LearningTagList'

const filterData1 = [
  { value: 'Architect', name: 'Architect' },
  { value: 'Buisiness_User', name: 'Buisiness User' },
  { value: 'Cloud_Operator', name: 'Cloud Operator' },
  { value: 'Data_Engineer', name: 'Data Engineer' },
  { value: 'Developer', name: 'Developer' },
  { value: 'Infrastructure_Engineer', name: 'Infrastructure Engineer' },
  { value: 'etc', name: '직무 기타' },
]

const filterData2 = [
  { value: 'Database', name: 'Database' },
  { value: 'Storage', name: 'Storage' },
  { value: 'Machine_Learning', name: 'Machine Learning' },
  { value: 'Cloud_Essentials', name: 'Cloud Essentials' },
  { value: 'Network_&_Content_Delivery', name: 'Network & Content Delivery' },
  { value: 'Serverless', name: 'Serverless' },
  { value: 'etc', name: '서비스 기타' },
]

const filterData3 = [
  { value: 'Digital_Course', name: 'Digital Course' },
  { value: 'Digital_Course_with_Lab', name: 'Digital Course With Lab' },
  { value: 'Exam_Preparation', name: 'Exam Preparation' },
]

const filterData4 = [
  { value: '1', name: '기초' },
  { value: '2', name: '중급' },
  { value: '3', name: '고급' },
]

const LearningFilterOpen = (props: ILearningFilterOpen) => {
  const { closeFilter } = props

  // 필터 목록
  const [filters, setFilters] = useState<IFilter[]>([])

  const [filter1, setFilter1] = useState(true)
  const [filter2, setFilter2] = useState(true)
  const [filter3, setFilter3] = useState(true)
  const [filter4, setFilter4] = useState(true)

  // 필터에 추가
  const addFilter = (v: IFilter) => setFilters([...filters, v])

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
                    <FiPlus onClick={e => addFilter(v)} />
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
                    <FiPlus onClick={e => addFilter(v)} />
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
                    <FiPlus onClick={e => addFilter(v)} />
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
                    <FiPlus onClick={e => addFilter(v)} />
                    <span>{v.name}</span>
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
        <LearningTagList filters={filters} />
      </div>
    </div>
  )
}

export default LearningFilterOpen
