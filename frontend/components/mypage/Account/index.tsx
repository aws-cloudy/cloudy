'use client'
import Dropdown from '@/components/common/Dropdown'
import styles from './Account.module.scss'
import { useState } from 'react'

interface OptionType {
  value: string
  label: string
}

const Account = ({ user }: any) => {
  const options: OptionType[] = [
    { value: 'Data Scientist', label: 'Data Scientist' },
    { value: 'Data2', label: 'Data2' },
    { value: 'Data3', label: 'Data3' },
    { value: 'Data4', label: 'Data4' },
    { value: 'Data5', label: 'Data5' },
  ]

  const [selectedJob, setSelectedJob] = useState<OptionType | null>(null)
  const [selectedService, setSelectedService] = useState<OptionType | null>(null)

  const handleJobChange = (selectedObtion: any) => setSelectedJob(selectedObtion)
  const handleServiceChange = (selectedObtion: any) => setSelectedService(selectedObtion)

  return (
    <section className={styles.section}>
      <div className={styles.intro}>기본 정보</div>
      <div className={styles.row}>
        <div className={styles.title}>이름</div>
        <div className={styles.block}>{user.name}</div>
      </div>
      <div className={styles.row}>
        <div className={styles.title}>이메일</div>
        <div className={styles.block}>{user.email}</div>
      </div>
      <div className={styles.row}>
        <div className={styles.title}>직무</div>
        <Dropdown
          options={options}
          value={selectedJob}
          onChange={handleJobChange}
          placeholder="직무"
          height={48}
          width={400}
        />
      </div>
      <div className={styles.row}>
        <div className={styles.title}>관심 서비스</div>
        <Dropdown
          options={options}
          value={selectedService}
          onChange={handleServiceChange}
          placeholder="관심 서비스"
          height={48}
          width={400}
        />
        <div className={styles.switchButton}>변경</div>
      </div>
    </section>
  )
}

export default Account
