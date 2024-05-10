'use client'

import axios from 'axios'
import { getSession } from 'next-auth/react'

export const client = axios.create({
  baseURL: '/cloudy-api',
})

client.interceptors.request.use(
  async config => {
    const session = await getSession()
    if (session?.accessToken) {
      const token = session.accessToken
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  error => {
    throw new Error(error)
  },
)

// 응답 인터셉터
client.interceptors.response.use(
  res => res,
  err => Promise.reject(alert(err.response.data.errorMap.type.message)),
)

export const searchClient = axios.create({
  baseURL: '/cloudy-search-api',
})

export const chatClient = axios.create({
  baseURL: '/cloudy-chat-api',
})
