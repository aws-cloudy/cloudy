import GoogleProvider from 'next-auth/providers/google'
import CognitoProvider from 'next-auth/providers/cognito'
import { NextAuthOptions } from 'next-auth'
import { checkUserExists } from './cognito'

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
    async signIn({ user, account, profile }) {
      // 사용자의 이메일을 기반으로 데이터베이스에서 사용자 존재 여부를 확인
      const userExists = await checkUserExists(user.email)
      if (!userExists) {
        // 데이터베이스에 사용자가 없으면 NewUser 에러를 발생시켜 리다이렉션 유도
        throw new Error('NewUser')
      }
      return true
    },
    async redirect({ url, baseUrl }) {
      if (url.includes('error=NewUser')) {
        return `${baseUrl}/join`
      }
      return baseUrl
    },

    // 토큰을 세션에 추가하는 콜백
    async jwt({ token, user, account, profile }: any) {
      // Cognito로부터 받은 사용자 지정 클레임을 JWT 토큰에 추가
      if (account?.provider === 'cognito' && user) {
        token.job_id = profile.job_id
        token.service_id = profile.service_id
        token.user = user
        token.account = account
        token.profile = profile
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
      session.user.job_id = token.job_id
      session.user.service_id = token.service_id
      return session
    },
  },
  secret: process.env.NEXT_PUBLIC_SECRET,
}
