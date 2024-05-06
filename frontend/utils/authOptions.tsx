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
    async signIn({ user, profile, email }: any) {
      let username
      if (profile?.identities) {
        // 구글 로그인의 경우, Cognito에 등록된 Username 사용
        username = `google_${profile.identities[0].userId}` // 예시, 실제 값은 확인 필요
      } else {
        // 일반 Cognito 사용자의 경우 이메일을 사용
        username = user.email
      }
      const userExists = await checkUserExists(username)
      console.log('유저 이메일', username)
      console.log('이멩', email)
      console.log('존재', userExists)
      console.log('유저이', user.isNewUser)
      if (!user.isNewUser) {
        user.isNewUser = true
        return `/join`
      }
      return true
    },

    // async redirect({ url, baseUrl }) {
    //    // URL 쿼리 파라미터 확인
    //    const urlParams = new URL(url, baseUrl);
    //    const isNewUser = urlParams.searchParams.get("join");
    //    console.log('파람', urlParams);
    //    console.log('존재에에', isNewUser)
    //   //  if (isNewUser === 'true') {
    //   //      return `${baseUrl}/join`; // 신규 사용자면 /join으로 리다이렉션
    //   //  }
    //    return baseUrl; // 그 외의 경우 기본 URL로 리다이렉션
    // },

    // 토큰을 세션에 추가하는 콜백
    async jwt({ token, user, account, profile }: any) {
      if (user) {
        const userExists = await checkUserExists(user.email)
        token.isNewUser = !userExists // 존재하지 않는 사용자라면 true
        if (user?.isNewUser) {
          token.isNewUser = user.isNewUser
        }
        // Cognito로부터 받은 사용자 지정 클레임을 JWT 토큰에 추가
        if (account?.provider === 'cognito' && user) {
          // 프로필에서 job_id, service_id를 추출해 JWT 토큰에 추가
          token.job_id = user?.job_id || profile?.['custom:job_id']
          token.service_id = user?.service_id || profile?.['custom:service_id']
          token.jobId = user?.jobId || profile?.['custom:job_id']
          token.serviceId = user?.serviceId || profile?.['custom:service_id']
          token.user = user
          token.account = account
          token.profile = profile
          token.accessToken = account.access_token
          token.id = account.providerAccountId
        }
        return token
      }
    },
    // 세션 데이터에 accessToken 추가
    async session({ session, token }: any) {
      if (token) {
        session.user.isNewUser = token.isNewUser
        session.accessToken = token.accessToken as string
        session.user.id = token.id as string
        session.user.uuid = token.sub as string
        session.token = token
        session.user.jobId = token.job_id
        session.user.serviceId = token.service_id
      }
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
