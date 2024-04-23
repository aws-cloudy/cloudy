'use client'
import Select from 'react-select'
import styles from './indext.module.scss'
import '@/styles/theme.scss'
import ProgressBar from '../ProgressBar'
import { useState } from 'react'

export default function RegistInfo() {
  const options = [
    { value: 'Data Scientist', label: 'Data Scientist' },
    { value: 'Data2', label: 'Data2' },
    { value: 'Data3', label: 'Data3' },
    { value: 'Data4', label: 'Data4' },
    { value: 'Data5', label: 'Data5' },
  ]

  const customStyles = {
    control: (base: any) => ({
      ...base,
      height: 50,
      minheight: 50,
    }),
  }

  const [currentStep, setCurrentStep] = useState(0)
  const totalSteps = 1

  const handleNextStep = () => {
    if (currentStep < totalSteps) {
      setCurrentStep(currentStep + 1)
    }
  }

  return (
    <div className={styles.section}>
      <div className={styles.title}>내 정보 등록</div>
      <div className={styles.grayText}>강의 추천을 위해 추가 정보를 입력해주세요.</div>
      <ProgressBar currentStep={currentStep} totalSteps={totalSteps} />
      <Select
        options={options}
        className={styles.select}
        placeholder="직무"
        theme={theme => ({
          ...theme,
          colors: {
            ...theme.colors,
            primary25: '#ff9900',
            primary75: 'black',
            primary: 'black',
          },
        })}
        styles={customStyles}
      />
      <Select
        options={options}
        className={styles.select}
        placeholder="관심 서비스"
        theme={theme => ({
          ...theme,
          colors: {
            ...theme.colors,
            primary25: '#ff9900',
            primary75: 'black',
            primary: 'black',
          },
        })}
        styles={customStyles}
      />
      <div className={styles.blackText}>등록한 정보는 마이페이지에서 변경할 수 있습니다.</div>

      <button className={styles.submit} onClick={handleNextStep}>
        확인
      </button>
    </div>
  )
}
