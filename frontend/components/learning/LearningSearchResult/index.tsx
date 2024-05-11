import styles from './LearningSearchResult.module.scss'
import { MdOutlineGridView } from 'react-icons/md'
import { LuAlignJustify } from 'react-icons/lu'
import { useLearninglayout, useLayoutActions } from '@/stores/layout'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { useRouter, useSearchParams } from 'next/navigation'
import { useEffect, useState } from 'react'
import { useLearningActions, useLearningOriginalQuery } from '@/stores/learning'

const LearningSearchResult = () => {
  const params = useSearchParams()
  const router = useRouter()

  const [filterCount, setFilterCount] = useState<number>(0)

  const keyword = params.get('query') || ''

  // 레이아웃
  const layout = useLearninglayout()
  const { setLearningLayout } = useLayoutActions()
  const onChangeLearningLayout = (v: 'grid' | 'justify') => {
    setLearningLayout(v)
  }
  const { isTablet } = useResponsiveWidth()

  // 원본 검색어
  const originalQuery = useLearningOriginalQuery()
  const { setLearningOriginalQuery } = useLearningActions()
  const searchWithOriginalQuery = () => {
    setLearningOriginalQuery('')
    router.push(`/learning?oquery=${keyword}`)
  }

  const resetFilter = () => {
    router.push(`/learning?query=${keyword}`)
    setLearningOriginalQuery('')
  }

  useEffect(() => {
    setFilterCount(
      params.getAll('job').length +
        params.getAll('service').length +
        params.getAll('type').length +
        params.getAll('difficulty').length,
    )
  }, [params])

  return (
    <div className={styles.container}>
      <div>
        <div>{originalQuery && <div>{`수정된 검색어 '${originalQuery}' 검색결과`}</div>}</div>
        {keyword && originalQuery && <button onClick={searchWithOriginalQuery}>{keyword}로 검색`</button>}
      </div>
      <div className={styles.rightWrap}>
        <div
          className={`${styles.filterText} ${filterCount ? styles.black : styles.gray}`}
          onClick={e => resetFilter()}
        >
          필터 초기화
        </div>
        {!isTablet && (
          <>
            <div className={styles.iconWrap} onClick={e => onChangeLearningLayout('grid')}>
              <MdOutlineGridView size="1.2em" color={layout === 'grid' ? '#1B1D1F' : '#CCCCCC'} />
            </div>
            <div className={styles.iconWrap} onClick={e => onChangeLearningLayout('justify')}>
              <LuAlignJustify size="1.2em" color={layout === 'justify' ? '#1B1D1F' : '#CCCCCC'} />
            </div>
          </>
        )}
      </div>
    </div>
  )
}

export default LearningSearchResult
