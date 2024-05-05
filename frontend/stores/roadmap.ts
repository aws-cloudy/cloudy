import { create } from 'zustand'

interface IRoadmapStore {
  searchValue: {
    job: string
    service: string
    keyword: string
  }
  actions: {
    setRoadmapSearchValue: (v: { job: string; service: string; keyword: string }) => void
  }
}

const roadmapStore = create<IRoadmapStore>(set => ({
  searchValue: {
    job: '',
    service: '',
    keyword: '',
  },
  actions: {
    setRoadmapSearchValue: value => set({ searchValue: value }),
  },
}))

export const useRoadmapSearchValue = () => roadmapStore(state => state.searchValue)

export const useRoadmapActions = () => roadmapStore(state => state.actions)
