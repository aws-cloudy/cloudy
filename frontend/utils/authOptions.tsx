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
    async signIn({ user }: any) {
      const userExists = await checkUserExists(user.email)
      if (!userExists) {
        user.isNewUser = true // 신규 사용자 플래그 설정
        return true
      }
      return true
    },
    async redirect({ url, baseUrl, session }: any) {
      // 세션에서 신규 사용자 플래그를 확인하여 리다이렉션 처리
      if (session?.user?.isNewUser) {
        return `${baseUrl}/join`
      }
      return baseUrl
    },

    // 토큰을 세션에 추가하는 콜백
    async jwt({ token, user, account, profile }: any) {
      if (user?.isNewUser) {
        token.isNewUser = user.isNewUser
      }
      // Cognito로부터 받은 사용자 지정 클레임을 JWT 토큰에 추가
      if (account?.provider === 'cognito' && user) {
        // 프로필에서 job_id, service_id를 추출해 JWT 토큰에 추가
        token.job_id = user?.job_id || profile?.['custom:job_id']
        token.service_id = user?.service_id || profile?.['custom:service_id']
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
      if (token?.isNewUser) {
        session.user.isNewUser = token.isNewUser
      }
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
  pages: {
    newUser: '/join',
  },
}
