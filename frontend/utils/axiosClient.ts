'use client'

import axios from 'axios'

export const client = axios.create({
  baseURL: '/cloudy-api',
})

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
