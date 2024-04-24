'use client'

import React, { useState } from 'react'
import { ILearningFilterOpen } from '@/types/learning'
import styles from './LearningFilterOpen.module.scss'
import { MdOutlineClose } from 'react-icons/md'
import { IoIosArrowDown } from 'react-icons/io'
import { IoIosArrowUp } from 'react-icons/io'
import { FiPlus } from 'react-icons/fi'
import { FiMinus } from 'react-icons/fi'
import LearningTagList from '../LearningTagList'

const LearningFilterOpen = (props: ILearningFilterOpen) => {
  const { closeFilter } = props

  const [filter1, setFilter1] = useState(true)
  const [filter2, setFilter2] = useState(true)

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
                <div className={styles.itemListItem}>
                  <FiPlus />
                  <span>data scientist</span>
                </div>
                <div className={styles.itemListItem}>
                  <FiPlus />
                  <span>Alliance Lead</span>
                </div>
                <div className={styles.itemListItem}>
                  <FiMinus />
                  <span>Account/Sales Manager</span>
                </div>
              </div>
            )}
          </div>
          <div className={styles.ItemWrap}>
            <div className={styles.ItemTitle} onClick={e => setFilter2(!filter2)}>
              <span>난이도</span>
              {filter2 ? <IoIosArrowUp /> : <IoIosArrowDown />}
            </div>
            {filter2 && (
              <div className={styles.itemList}>
                <div className={styles.itemListItem}>
                  <FiPlus />
                  <span>기초</span>
                </div>
                <div className={styles.itemListItem}>
                  <FiPlus />
                  <span>중급</span>
                </div>
                <div className={styles.itemListItem}>
                  <FiMinus />
                  <span>고급</span>
                </div>
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
