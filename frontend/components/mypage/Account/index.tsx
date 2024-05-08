'use client'
import Dropdown from '@/components/common/Dropdown'
import styles from './Account.module.scss'
import { useEffect, useState } from 'react'
import { signIn } from 'next-auth/react'

interface OptionType {
  value: string
  label: string
}

const Account = ({ user }: any) => {
  const jobOptions: OptionType[] = [
    { value: '0', label: 'Architect' },
    { value: '1', label: 'Business User' },
    { value: '2', label: 'Cloud Operator' },
    { value: '3', label: 'Data Engineer' },
    { value: '4', label: 'Developer' },
    { value: '5', label: 'Infrastructure Engineer' },
  ]
  const serviceOptions: OptionType[] = [
    { value: '0', label: 'Database' },
    { value: '1', label: 'Storage' },
    { value: '2', label: 'Machine Learning' },
    { value: '3', label: 'Cloud Essentials' },
    { value: '4', label: 'Network & ContentDelivery' },
    { value: '5', label: 'Serverless' },
  ]

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
