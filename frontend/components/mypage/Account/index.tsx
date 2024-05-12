'use client'
import Dropdown from '@/components/common/Dropdown'
import styles from './Account.module.scss'
import { useEffect, useState } from 'react'
import { signIn } from 'next-auth/react'
import { jobOptions, jobSelections, serviceOptions } from '@/constants/user'

interface OptionType {
  value: string
  label: string
}

const Account = ({ user }: any) => {
  const findOptionById = (options: any[], id: any) => options.find((option: { value: any }) => option.value === id)

  const [selectedJob, setSelectedJob] = useState<OptionType | null>(findOptionById(jobOptions, user.jobId))
  const [selectedService, setSelectedService] = useState<OptionType | null>(
    findOptionById(serviceOptions, user.serviceId),
  )

  const handleJobChange = (selectedObtion: any) => setSelectedJob(selectedObtion)
  const handleServiceChange = (selectedObtion: any) => setSelectedService(selectedObtion)

  const handleUpdateUser = async () => {
    const response = await fetch('api/user/update', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: user.username,
        jobId: selectedJob?.value,
        serviceId: selectedService?.value,
      }),
    })
    if (response.ok) {
      alert('성공적으로 업데이트되었습니다.')
      // 세션 정보 갱신
      signIn('cognito', { callbackUrl: '/mypage' })
    } else {
      alert('업데이트에 실패했습니다.')
    }
  }

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
          options={jobOptions}
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
          options={serviceOptions}
          value={selectedService}
          onChange={handleServiceChange}
          placeholder="관심 서비스"
          height={48}
          width={400}
        />
        <button className={styles.switchButton} onClick={handleUpdateUser}>
          변경
        </button>
      </div>
    </section>
  )
}

export default Account
