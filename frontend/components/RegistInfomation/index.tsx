'use client'

import styles from './RegistInfomation.module.scss'
import '@/styles/theme.scss'
import ProgressBar from '../ProgressBar'
import { useState } from 'react'
import Dropdown from '../common/Dropdown'
import { signIn } from 'next-auth/react'
import { OptionType, jobSelections, serviceOptions } from '@/constants/user'

export default function RegistInfomation({ username }: { username: string }) {
  const [selectedJob, setSelectedJob] = useState<OptionType | null>(null)
  const [selectedService, setSelectedService] = useState<OptionType | null>(null)
  const [confirmScreen, setConfirmScreen] = useState(false)

  const [currentStep, setCurrentStep] = useState(0)
  const totalSteps = 1

  const handleJobChange = (selectedObtion: any) => setSelectedJob(selectedObtion)
  const handleServiceChange = (selectedObtion: any) => setSelectedService(selectedObtion)

  const handleConfirm = () => {
    if (selectedJob && selectedService) {
      if (currentStep < totalSteps) {
        setCurrentStep(currentStep + 1)
      }
      setConfirmScreen(true)
    } else {
      alert('직무와 서비스를 선택해주세요.')
    }
  }
  const handleCancel = () => {
    setCurrentStep(currentStep - 1)
    setConfirmScreen(false)
  }

  const submit = async () => {
    const response = await fetch('/api/user/update', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, jobId: selectedJob?.value, serviceId: selectedService?.value }),
    })
      .then(res => {
        console.log('update 성공', res)
        console.log('job', selectedJob?.value)
        console.log('servive', selectedService?.value)
        // 정보가 성공적으로 등록되면, 메인 페이지로 리다이렉트
        signIn('cognito', { callbackUrl: '/' })
      })
      .catch(err => {
        // 에러 처리
        console.error('Failed to update user information')
      })
    // console.log('결과', response)
    // if (response.ok) {
    //   // 정보가 성공적으로 등록되면, 메인 페이지로 리다이렉트
    //   signIn('cognito', { callbackUrl: '/' })
    // } else {
    //   // 에러 처리
    //   console.error('Failed to update user information')
    // }
  }

  return (
    <div className={styles.section}>
      <div className={styles.title}>내 정보 등록</div>
      <div className={styles.grayText}>강의 추천을 위해 추가 정보를 입력해주세요.</div>
      <ProgressBar currentStep={currentStep} totalSteps={totalSteps} />

      {confirmScreen ? (
        <>
          <div className={styles.blackText}>이하 내용으로 등록하시겠습니까?</div>
          <div className={styles.info}>
            <div className={styles.infoContainer}>
              <div className={styles.infoTitle}>직무</div>
              <div id="selectedJob">{selectedJob?.label}</div>
            </div>
            <div className={styles.infoContainer}>
              <div className={styles.infoTitle}>관심 서비스</div>
              <div>{selectedService?.label}</div>
            </div>
          </div>
          <button className={styles.cancel} onClick={handleCancel}>
            취소
          </button>
          <button className={styles.submit} onClick={submit}>
            확인
          </button>
        </>
      ) : (
        <>
          <div className={styles.blackText}>등록한 정보는 마이페이지에서 변경할 수 있습니다.</div>
          <div className={styles.drops}>
            <Dropdown
              options={jobSelections}
              value={selectedJob}
              onChange={handleJobChange}
              placeholder="직무"
              width={730}
            />

            <Dropdown
              options={serviceOptions}
              value={selectedService}
              onChange={handleServiceChange}
              placeholder="관심 서비스"
              width={730}
            />
          </div>
          <button className={styles.submit} onClick={handleConfirm} id="selected">
            확인
          </button>
        </>
      )}
    </div>
  )
}
