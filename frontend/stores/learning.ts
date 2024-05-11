import { create } from 'zustand'

interface ILearningState {
  originalQuery: string
  actions: {
    setLearningOriginalQuery: (value: string) => void
  }
}

const learningStore = create<ILearningState>(set => ({
  originalQuery: '',
  actions: {
    setLearningOriginalQuery: value => set({ originalQuery: value }),
  },
}))

export const useLearningOriginalQuery = () => learningStore(state => state.originalQuery)

export const useLearningActions = () => learningStore(state => state.actions)
