import { IHashtag } from '@/types/community'
import { create } from 'zustand'

interface IKeywordStore {
  selected: IHashtag[]
  keyword: string
  actions: {
    setSelected: (by: IHashtag[]) => void
    setKeyword: (by: string) => void
  }
}

const useCommuSearchStore = create<IKeywordStore>(set => ({
  selected: [],
  keyword: '',
  actions: {
    setSelected: by => set({ selected: by }),
    setKeyword: by => set({ keyword: by }),
  },
}))

export const useSelectedTags = () => useCommuSearchStore(state => state.selected)
export const useCommuSearchKeyword = () => useCommuSearchStore(state => state.keyword)
export const useCommuSearchActions = () => useCommuSearchStore(state => state.actions)
