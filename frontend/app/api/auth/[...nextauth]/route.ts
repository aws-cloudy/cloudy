import NextAuth from 'next-auth/next'
import GoogleProvider from 'next-auth/providers/google'
import CognitoProvider from 'next-auth/providers/cognito'

const handler = NextAuth({
  //Providers 소셜 로그인 서비스 코드
  providers: [
    GoogleProvider({
      clientId: process.env.GOOGLE_CLIENT_ID || '',
      clientSecret: process.env.GOOGLE_CLIENT_SECRET || '',
    }),
    CognitoProvider({
      clientId: process.env.COGNITO_CLIENT_ID as string,
      clientSecret: process.env.COGNITO_CLIENT_SECRET as string,
      issuer: process.env.COGNITO_ISSUER,
    }),
  ],
  callbacks: {
    // 토큰을 세션에 추가하는 콜백
    async jwt({ token, account }) {
      if (account) {
        token.accessToken = account.access_token
      }
      return token
    },
    // 세션 데이터에 accessToken 추가
    async session({ session, token }) {
      session.accessToken = token.accessToken as string
      return session
    },
  },
})

export { handler as GET, handler as POST }
