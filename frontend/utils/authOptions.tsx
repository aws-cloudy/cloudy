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
    async signIn({ user, profile }: any) {
      let username
      if (profile?.identities) {
        // 구글 로그인의 경우, Cognito에 등록된 Username 사용
        username = `google_${profile.identities[0].userId}`
      } else {
        // 일반 Cognito 사용자의 경우 이메일을 사용
        username = user.email
        username = user.email
      }
      user.username = username
      // const { exists, hasJobId } = await checkUserExists(username)
      console.log(user)
      // if (!exists || !hasJobId) {
      // user.isNewUser = false

      // return `/join`
      // return true
      // }
      return true
    },

    // 토큰을 세션에 추가하는 콜백
    async jwt({ token, user, account, profile }: any) {
      if (user) {
        // const userExists  = await checkUserExists(user.email);
        // token.isNewUser = !userExists ;  // 존재하지 않는 사용자라면 true
        // 프로필에서 job_id, service_id를 추출해 JWT 토큰에 추가
        token.jobId = user?.jobId || profile?.job_id
        token.serviceId = user?.serviceId || profile?.service_id
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
      if (token) {
        session.user.isNewUser = token.isNewUser
        session.accessToken = token.accessToken as string
        session.user.id = token.id as string
        session.user.uuid = token.sub as string
        session.token = token
        session.user.jobId = token.jobId
        session.user.serviceId = token.serviceId
      }
      return session
    },
  },
  secret: process.env.NEXT_PUBLIC_SECRET,
  pages: {
    newUser: '/join',
  },
}
