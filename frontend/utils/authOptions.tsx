import GoogleProvider from 'next-auth/providers/google'
import CognitoProvider from 'next-auth/providers/cognito'
import { NextAuthOptions } from 'next-auth'
import { checkUserExists, getUser } from './cognito'
import { redirect } from 'next/navigation'

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
    async signIn({ user, profile }: any) {
      let username
      if (profile?.identities) {
        // 구글 로그인의 경우, Cognito에 등록된 Username 사용
        username = `google_${profile.identities[0].userId}`
      } else {
        // 일반 Cognito 사용자의 경우 이메일을 사용
        username = user.email
      }
      user.username = username
      const { exists, hasJobId } = await checkUserExists(username)
      if (!exists || !hasJobId) {
        return `/join?auth=${encodeURIComponent(username)}`
      }
      return true
    },
    async jwt({ token, user, account, profile }: any) {
      if (user) {
        token.username = user.username
        token.jobId = user?.jobId || profile?.job_id
        token.serviceId = user?.serviceId || profile?.service_id
        token.accessToken = account.access_token
        token.id = account.providerAccountId
      }
      return token
    },

    async session({ session, token }: any) {
      session.user.username = token.username
      session.accessToken = token.accessToken as string
      session.user.id = token.id as string
      session.user.uuid = token.sub as string
      session.user.jobId = token.jobId
      session.user.serviceId = token.serviceId
      return session
    },
  },
  secret: process.env.NEXT_PUBLIC_SECRET,
  pages: {
    newUser: '/join',
  },
}
