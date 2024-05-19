'use client'

import axios from 'axios'
import { getSession, signOut } from 'next-auth/react'
import { getUserRefreshToken } from './cognito'

export const client = axios.create({
  baseURL: '/cloudy-api',
})

// 응답 인터셉터
client.interceptors.response.use(
  res => res,
  async error => {
    const originalRequest = error.config
    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true
      const session = await getSession()
      // getUserRefreshToken 함수를 통해 Refresh Token을 가져오기
      const refreshToken = await getUserRefreshToken(session?.user?.username as string)

      if (refreshToken) {
        try {
          // Refresh Token을 사용하여 새로운 Access Token을 얻음
          const newAccessToken = await getUserRefreshToken(session?.user?.username as string)

          // 새로운 Access Token으로 요청 재시도
          originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`
          return axios(originalRequest)
        } catch (error) {
          // Refresh Token이 만료된 경우 또는 다른 문제 발생 시 로그아웃 처리
          console.error('Refresh Token 만료!!:', error)
          await signOut()
        }
      } else {
        // Refresh Token이 없는 경우
        console.error('Refresh Token이 없습니다.')
        await signOut()
      }
    }
    return Promise.reject(error)
  },
)

client.interceptors.request.use(
  async config => {
    const session = await getSession()
    const accessToken = session?.accessToken

    if (accessToken) {
      // console.log(accessToken)
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

// 응답 인터셉터
chatClient.interceptors.response.use(
  res => res,
  async error => {
    const originalRequest = error.config
    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true
      const session = await getSession()
      if (!session) return
      // getUserRefreshToken 함수를 통해 Refresh Token을 가져오기
      const refreshToken = await getUserRefreshToken(session?.user?.username as string)

      if (refreshToken) {
        try {
          // Refresh Token을 사용하여 새로운 Access Token을 얻음
          const newAccessToken = await getUserRefreshToken(session?.user?.username as string)

          // 새로운 Access Token으로 요청 재시도
          originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`
          return axios(originalRequest)
        } catch (error) {
          // Refresh Token이 만료된 경우 또는 다른 문제 발생 시 로그아웃 처리
          console.error('Refresh Token 만료!!:', error)
          await signOut()
        }
      } else {
        // Refresh Token이 없는 경우
        console.error('Refresh Token이 없습니다.')
        await signOut()
      }
    }
    return Promise.reject(error)
  },
)

chatClient.interceptors.request.use(
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
