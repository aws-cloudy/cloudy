import 'next-auth'

// User 모델 확장
declare module 'next-auth' {
  /**
   * Represents the user's session.
   */
  interface Session {
    user: {
      // isNewUser?: boolean // 신규 사용자 플래그를 추가
      id?: string
      name?: string
      email?: string
      username: string
    }
  }
}

declare module 'next-auth/jwt' {
  /** Represents the JWT token structure. */
  interface JWT {
    // username: string
    // isNewUser?: boolean // 신규 사용자 플래그를 추가
  }
}
