import styles from './LearningSearchResult.module.scss'
import { MdOutlineGridView } from 'react-icons/md'
import { LuAlignJustify } from 'react-icons/lu'
import { useLearninglayout, useSearchActions } from '@/stores/layout'
import { useResponsiveWidth } from '@/hooks/useResonsiveWidth'
import { useSearchParams } from 'next/navigation'

const LearningSearchResult = () => {
  const params = useSearchParams()
  const keyword = params.get('query') || ''

  // 레이아웃
  const layout = useLearninglayout()
  const { setLearningLayout } = useSearchActions()
  const onChangeLearningLayout = (v: 'grid' | 'justify') => {
    setLearningLayout(v)
  }
  const { isTablet } = useResponsiveWidth()

  const resetFilter = () => console.log('reset')

  return (
    <div className={styles.container}>
      <div>{keyword && <div>{`'${keyword}' 검색결과`}</div>}</div>
      <div className={styles.rightWrap}>
        <div className={`${styles.filterText} ${1 ? styles.black : styles.gray}`} onClick={e => resetFilter()}>
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
