import 'next-auth'

// User 모델 확장
declare module 'next-auth' {
  interface Session {
    user: {
      id?: string
      name?: string
      email?: string
      username: string
      jobId: number
      serviceId: number
    }
  }
}

declare module 'next-auth/jwt' {
  interface JWT {
    jobId: number
    serviceId: number
  }
}
