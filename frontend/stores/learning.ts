import { create } from 'zustand'

interface ILearningState {
  originalQuery: string
  filterCount: number
  actions: {
    setLearningOriginalQuery: (value: string) => void
    setLearningFilterCount: (value: number) => void
  }
}

const learningStore = create<ILearningState>(set => ({
  originalQuery: '',
  filterCount: 0,
  actions: {
    setLearningOriginalQuery: value => set({ originalQuery: value }),
    setLearningFilterCount: value => set({ filterCount: value }),
  },
}))

export const useLearningOriginalQuery = () => learningStore(state => state.originalQuery)
export const useLearningFilterCount = () => learningStore(state => state.filterCount)

export const useLearningActions = () => learningStore(state => state.actions)
