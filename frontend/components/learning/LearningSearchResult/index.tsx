import React from 'react'
import styles from './LearningSearchResult.module.scss'
import { MdOutlineGridView } from 'react-icons/md'
import { LuAlignJustify } from 'react-icons/lu'
import { useLearninglayout, useSearchActions } from '@/stores/search'
import { ILearningSearchResult } from '@/types/learning'

const LearningSearchResult = (props: ILearningSearchResult) => {
  const { keyword } = props

  // 레이아웃
  const layout = useLearninglayout()
  const { setLearningLayout } = useSearchActions()
  const onChangeLearningLayout = (v: 'grid' | 'justify') => {
    setLearningLayout(v)
  }

  return (
    <div className={styles.container}>
      <div>{keyword && <div>{`'${keyword}' 검색결과 총 1건`}</div>}</div>
      <div className={styles.rightWrap}>
        <div className={styles.filterText}>필터 초기화</div>
        <div className={styles.iconWrap} onClick={e => onChangeLearningLayout('grid')}>
          <LuAlignJustify size="1.2em" color={layout === 'grid' ? '#1B1D1F' : '#CCCCCC'} />
        </div>
        <div className={styles.iconWrap} onClick={e => onChangeLearningLayout('justify')}>
          <MdOutlineGridView size="1.2em" color={layout === 'justify' ? '#1B1D1F' : '#CCCCCC'} />
        </div>
      </div>
    </div>
  )
}

export default LearningSearchResult
