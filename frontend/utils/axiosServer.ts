import axios from 'axios'

const server = axios.create({
  baseURL: 'https://3m8faj87ji.execute-api.ap-northeast-2.amazonaws.com/prod/api/v1',
})

// 응답 인터셉터
server.interceptors.response.use(
  res => res,
  err => Promise.reject(alert(err.response.data.errorMap.type.message)),
)

export default server
