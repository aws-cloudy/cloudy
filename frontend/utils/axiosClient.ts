'use client'

import axios from 'axios'
import { getSession } from 'next-auth/react'

export const client = axios.create({
  baseURL: '/cloudy-api',
})

// 응답 인터셉터
client.interceptors.response.use(
  res => res,
  err => Promise.reject(err),
)

client.interceptors.request.use(
  async config => {
    const session = await getSession()
    const accessToken = session?.accessToken

    if (accessToken) {
      config.headers['Authorization'] = `Bearer ${accessToken}`
    }

    return config
  },
  error => {
    return Promise.reject(error.response)
  },
)

export const searchClient = axios.create({
  baseURL: '/cloudy-search-api',
})

export const chatClient = axios.create({
  baseURL: '/cloudy-chat-api',
})
