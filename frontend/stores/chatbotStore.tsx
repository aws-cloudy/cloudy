import { create } from 'zustand'

interface IChatbotStore {
  isChatbotOpen: boolean
  chatbotType: 'oudy' | 'cla' | 'ama' | 'john' | 'main'
  actions: {
    setIsChatbotOpen: (by: boolean) => void
    setChatbotType: (by: 'oudy' | 'cla' | 'ama' | 'john' | 'main') => void
  }
}

const useChatbotStore = create<IChatbotStore>(set => ({
  isChatbotOpen: false,
  chatbotType: 'main',
  actions: {
    setIsChatbotOpen: by => set({ isChatbotOpen: by }),
    setChatbotType: by => set({ chatbotType: by }),
  },
}))

export const useIsChatbotOpen = () => useChatbotStore(state => state.isChatbotOpen)
export const useChatbotType = () => useChatbotStore(state => state.chatbotType)
export const useChatbotActions = () => useChatbotStore(state => state.actions)
