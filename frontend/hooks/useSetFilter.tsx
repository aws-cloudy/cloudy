import { useLearningActions } from '@/stores/learning'
import { IFilter } from '@/types/learning'

// category에 따라 필터 설정
export const useSetFilter = (v: IFilter) => {
  const { setJobFilter, setServiceFilter, setTypeFilter, setDifficultyFilter } = useLearningActions()

  return () => {
    switch (v.category) {
      case 'job':
        setJobFilter(v)
        break
      case 'service':
        setServiceFilter(v)
        break
      case 'type':
        setTypeFilter(v)
        break
      case 'difficulty':
        setDifficultyFilter(v)
        break
    }
  }
}
