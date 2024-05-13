import { getSession } from 'next-auth/react'
import { boolean } from 'zod'
import { create } from 'zustand'

interface IAuthStore {
  isLogin: boolean
  token: Promise<string | null> | string | null
  username: string | null
  userId: string | null
  actions: {
    setUser: () => void
    resetUser: () => void
  }
}

const useAuthStore = create<IAuthStore>((set, get) => ({
  isLogin: false,
  username: null,
  token: null,
  userId: null,
  actions: {
    setUser: async () => {
      const session = await getSession()
      if (!session?.accessToken) return
      set({ isLogin: true, token: session.accessToken, username: session.user.name, userId: session.user.id })
    },
    resetUser: () => {
      set({ isLogin: false, token: null, username: null, userId: null })
    },
  },
}))

export const useIsLogin = () => useAuthStore(state => state.isLogin)
export const useToken = () => useAuthStore(state => state.token)
export const useUsername = () => useAuthStore(state => state.username)
export const useUserId = () => useAuthStore(state => state.userId)
export const useTokenActions = () => useAuthStore(state => state.actions)
