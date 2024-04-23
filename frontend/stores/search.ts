import { create } from 'zustand'

interface ISearchState {
  learninglayout: 'grid' | 'justify'
  actions: {
    setLearningLayout: (value: 'grid' | 'justify') => void
  }
}

const searchStore = create<ISearchState>(set => ({
  learninglayout: 'grid',
  actions: {
    setLearningLayout: value => set({ learninglayout: value }),
  },
}))

export const useLearninglayout = () => searchStore(state => state.learninglayout)

export const useSearchActions = () => searchStore(state => state.actions)
