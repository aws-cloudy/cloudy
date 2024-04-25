import { create } from 'zustand'

interface IKeywordStore {
  selected: string[]
  setSelected: (by: string[]) => void
}

const useKeywordStore = create<IKeywordStore>(set => ({
  selected: [],
  setSelected: by => set({ selected: by }),
}))

export const useSelectedKeywords = () => useKeywordStore(state => state.selected)
export const useSetSelectedKeywords = () => useKeywordStore(state => state.setSelected)
