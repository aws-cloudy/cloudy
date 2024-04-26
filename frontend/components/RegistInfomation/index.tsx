'use client'
import Select from 'react-select'
import styles from './RegistInfomation.module.scss'
import '@/styles/theme.scss'
import ProgressBar from '../ProgressBar'
import { useState } from 'react'
import Dropdown from '../common/Dropdown'

interface OptionType {
  value: string
  label: string
}

export default function RegistInfomation() {
  const options: OptionType[] = [
    { value: 'Data Scientist', label: 'Data Scientist' },
    { value: 'Data2', label: 'Data2' },
    { value: 'Data3', label: 'Data3' },
    { value: 'Data4', label: 'Data4' },
    { value: 'Data5', label: 'Data5' },
  ]

  const [selectedJob, setSelectedJob] = useState<OptionType | null>(null)
  const [selectedService, setSelectedService] = useState<OptionType | null>(null)
  const [confirmScreen, setConfirmScreen] = useState(false)

  const [currentStep, setCurrentStep] = useState(0)
  const totalSteps = 1

  const handleJobChange = (selectedObtion: any) => setSelectedJob(selectedObtion)
  const handleServiceChange = (selectedObtion: any) => setSelectedService(selectedObtion)

  const handleConfirm = () => {
    if (currentStep < totalSteps) {
      setCurrentStep(currentStep + 1)
    }
    setConfirmScreen(true)
  }
  const handleCancel = () => {
    setCurrentStep(currentStep - 1)
    setConfirmScreen(false)
  }

  if (confirmScreen) {
    return (
      <div className={styles.section}>
        <div className={styles.title}>내 정보 등록</div>
        <div className={styles.grayText}>강의 추천을 위해 추가 정보를 입력해주세요.</div>
        <ProgressBar currentStep={currentStep} totalSteps={totalSteps} />
        <div className={styles.blackText}>이하 내용으로 등록하시겠습니까?</div>
        <div className={styles.info}>
          <div className={styles.infoContainer}>
            <div className={styles.infoTitle}>직무</div>
            <div id="selectedJob">{selectedJob ? selectedJob.label : '선택되지 않음'}</div>
          </div>
          <div className={styles.infoContainer}>
            <div className={styles.infoTitle}>관심 서비스</div>
            <div>{selectedService ? selectedService.label : '선택되지 않음'}</div>
          </div>
        </div>
        <button className={styles.cancel} onClick={handleCancel}>
          취소
        </button>
        <button className={styles.submit} onClick={handleConfirm}>
          확인
        </button>
      </div>
    )
  }

  return (
    <div className={styles.section}>
      <div className={styles.title}>내 정보 등록</div>
      <div className={styles.grayText}>강의 추천을 위해 추가 정보를 입력해주세요.</div>
      <ProgressBar currentStep={currentStep} totalSteps={totalSteps} />
      <div className={styles.blackText}>등록한 정보는 마이페이지에서 변경할 수 있습니다.</div>
      <Dropdown options={options} value={selectedJob} onChange={handleJobChange} placeholder="직무" />
      <Dropdown options={options} value={selectedService} onChange={handleServiceChange} placeholder="관심 서비스" />

      <button className={styles.submit} onClick={handleConfirm} id="selected">
        확인
      </button>
    </div>
  )
}
