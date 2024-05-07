import axios from 'axios'

const axiosInstance = axios.create({
  baseURL: '/cloudy-api',
})

// 응답 인터셉터
axiosInstance.interceptors.response.use(
  res => res,
  err => Promise.reject(alert(err.response.data.errorMap.type.message)),
)

export default axiosInstance
