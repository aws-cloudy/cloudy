import { create } from 'zustand'

interface ILayoutState {
  learninglayout: 'grid' | 'justify'
  actions: {
    setLearningLayout: (value: 'grid' | 'justify') => void
  }
}

const layoutStore = create<ILayoutState>(set => ({
  learninglayout: 'grid',
  actions: {
    setLearningLayout: value => set({ learninglayout: value }),
  },
}))

export const useLearninglayout = () => layoutStore(state => state.learninglayout)

export const useSearchActions = () => layoutStore(state => state.actions)
