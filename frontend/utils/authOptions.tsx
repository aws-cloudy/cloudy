import GoogleProvider from 'next-auth/providers/google'
import CognitoProvider from 'next-auth/providers/cognito'
import { NextAuthOptions } from 'next-auth'

export const authOptions: NextAuthOptions = {
  //Providers 소셜 로그인 서비스 코드
  providers: [
    CognitoProvider({
      clientId: process.env.COGNITO_CLIENT_ID as string,
      clientSecret: process.env.COGNITO_CLIENT_SECRET as string,
      issuer: process.env.COGNITO_ISSUER,
      idToken: true,
      checks: 'nonce',
    }),
  ],
  callbacks: {
    // 토큰을 세션에 추가하는 콜백
    async jwt({ token, account }: any) {
      if (account) {
        token.accessToken = account.access_token
        token.id = account.providerAccountId
      }
      return token
    },
    // 세션 데이터에 accessToken 추가
    async session({ session, token }: any) {
      session.accessToken = token.accessToken as string
      session.user.id = token.id as string
      session.user.uuid = token.sub as string
      session.token = token
      return session
    },
  },
  secret: process.env.NEXTAUTH_SECRET,
}
