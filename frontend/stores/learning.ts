import { IFilter } from '@/types/learning'
import { create } from 'zustand'

interface ILearningState {
  jobs: IFilter[]
  services: IFilter[]
  types: IFilter[]
  difficulties: IFilter[]
  filterCount: number
  keyword: string
  actions: {
    setJobFilter: (v: IFilter) => void
    setServiceFilter: (v: IFilter) => void
    setTypeFilter: (v: IFilter) => void
    setDifficultyFilter: (v: IFilter) => void
    resetFilter: () => void
    setKeyword: (v: string) => void
  }
}

const learningStore = create<ILearningState>(set => ({
  jobs: [],
  services: [],
  types: [],
  difficulties: [],
  filterCount: 0,
  keyword: '',
  actions: {
    setJobFilter: v =>
      set(state => {
        const index = state.jobs.indexOf(v)
        return index === -1
          ? { ...state, jobs: [...state.jobs, v], filterCount: (state.filterCount += 1) }
          : { ...state, jobs: state.jobs.filter((_, i) => i !== index), filterCount: (state.filterCount -= 1) }
      }),
    setServiceFilter: v =>
      set(state => {
        const index = state.services.indexOf(v)
        return index === -1
          ? { ...state, services: [...state.services, v], filterCount: (state.filterCount += 1) }
          : { ...state, services: state.services.filter((_, i) => i !== index), filterCount: (state.filterCount -= 1) }
      }),
    setTypeFilter: v =>
      set(state => {
        const index = state.types.indexOf(v)
        return index === -1
          ? { ...state, types: [...state.types, v], filterCount: (state.filterCount += 1) }
          : { ...state, types: state.types.filter((_, i) => i !== index), filterCount: (state.filterCount -= 1) }
      }),
    setDifficultyFilter: v =>
      set(state => {
        const index = state.difficulties.indexOf(v)
        return index === -1
          ? { ...state, difficulties: [...state.difficulties, v], filterCount: (state.filterCount += 1) }
          : {
              ...state,
              difficulties: state.difficulties.filter((_, i) => i !== index),
              filterCount: (state.filterCount -= 1),
            }
      }),
    resetFilter: () => set({ jobs: [], services: [], types: [], difficulties: [], filterCount: 0 }),
    setKeyword: v => set({ keyword: v }),
  },
}))

export const usejobFilter = () => learningStore(state => state.jobs)
export const useServiceFilter = () => learningStore(state => state.services)
export const useTypeFilter = () => learningStore(state => state.types)
export const useDifficultyFilter = () => learningStore(state => state.difficulties)
export const useFilterCount = () => learningStore(state => state.filterCount)
export const useLearningKeyword = () => learningStore(state => state.keyword)

export const useLearningActions = () => learningStore(state => state.actions)
