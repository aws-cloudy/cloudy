import React from 'react'
import styles from './LearningSearchList.module.scss'
import LearningSearchListItem from '../LearningSearchListItem'
import { ILearningSearchList } from '@/types/learning'
import { useRouter } from 'next/navigation'

const LearningSearchList = (props: ILearningSearchList) => {
  const { list, setIsOpen, selected, setValue } = props

  const router = useRouter()

  // 자동 검색어 클릭시
  const onClick = (v: string) => {
    router.push(`/learning?query=${v}`)
    setIsOpen(false)
    setValue(v)
  }

  return (
    <div className={styles.container}>
      {list.map((item, idx) => (
        <LearningSearchListItem
          onClick={() => onClick(item.title)}
          key={item.documentId}
          item={item}
          selected={selected === idx}
        />
      ))}
    </div>
  )
}

export default LearningSearchList
