'use client'

import axios from 'axios'

const client = axios.create({
  baseURL: '/cloudy-api',
})

// 응답 인터셉터
client.interceptors.response.use(
  res => res,
  err => Promise.reject(alert(err.response.data.errorMap.type.message)),
)

export default client
