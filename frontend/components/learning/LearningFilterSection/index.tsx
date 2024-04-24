'use client'

import React, { useState } from 'react'
import styles from './LearningFilterSection.module.scss'
import LearningFilterOpen from '../LearningFilterOpen'
import LearningFilterClose from '../LearningFilterClose'

const LearningFilterSection = () => {
  const [open, setOpen] = useState(true)

  const handleOpen = (v: boolean) => setOpen(v)

  return (
    <article className={styles.article}>
      {open ? (
        <LearningFilterOpen closeFilter={() => handleOpen(false)} />
      ) : (
        <LearningFilterClose openFilter={() => handleOpen(true)} />
      )}
    </article>
  )
}

export default LearningFilterSection
