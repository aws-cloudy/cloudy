'use client'

import React, { useEffect, useState } from 'react'
import styles from './LearningFilterSection.module.scss'
import LearningFilterOpen from '../LearningFilterOpen'
import LearningFilterClose from '../LearningFilterClose'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'

const LearningFilterSection = () => {
  const [open, setOpen] = useState(true)

  const { isTablet } = useResponsiveWidth()

  const handleOpen = (v: boolean) => setOpen(v)

  useEffect(() => {
    isTablet && handleOpen(false)
  }, [isTablet])

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
