'use client'

import React, { useState } from 'react'
import styles from './LearningFilterSection.module.scss'
import LearningFilterOpen from '../LearningFilterOpen'
import LearningFilterClose from '../LearningFilterClose'

const LearningFilterSection = () => {
  const [open, setOpen] = useState(true)

  const handleOpen = (v: boolean) => setOpen(v)

  return (
    <aside className={`${styles.aside} ${open && styles.mobileAside}`}>
      {open ? (
        <LearningFilterOpen closeFilter={() => handleOpen(false)} />
      ) : (
        <LearningFilterClose openFilter={() => handleOpen(true)} />
      )}
    </aside>
  )
}

export default LearningFilterSection
