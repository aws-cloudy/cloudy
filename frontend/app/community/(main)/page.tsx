'use client'

import styles from './page.module.scss'
import PageTitle from '@/components/common/PageTitle'
import CommunitySidebar from '@/components/community/main/CommunitySidebar'
import CommunityList from '@/components/community/main/CommunityList'
import { useState } from 'react'
import { IoCloseOutline } from 'react-icons/io5'
import { BiSearch } from 'react-icons/bi'
import Layout from '@/components/common/Layout'

const Communitypage = () => {
  const [isOpen, setIsOpen] = useState(false)

  return (
    <Layout>
      <div className={styles.content}>
        <div className={styles.titleSection}>
          <PageTitle />
          <div className={styles.openButton}>
            <BiSearch
              className={styles.icon}
              onClick={() => {
                setIsOpen(true)
              }}
            />
          </div>
        </div>
        <CommunityList />
      </div>
      <div className={`${styles.sidebar} ${isOpen && styles.open}`}>
        {isOpen && <IoCloseOutline className={styles.close} onClick={() => setIsOpen(false)} />}
        <CommunitySidebar />
      </div>
    </Layout>
  )
}

export default Communitypage
