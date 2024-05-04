import GoogleProvider from 'next-auth/providers/google'
import CognitoProvider from 'next-auth/providers/cognito'
import { NextAuthOptions } from 'next-auth'

// const cognitoClient = new CognitoIdentityProviderClient({
//     region: process.env.AWS_REGION as string,
//     credentials: {
//       accessKeyId: process.env.AWS_ACCESS_KEY_ID as string,
//       secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY as string,
//     },
//   })

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
    // async redirect({ url, baseUrl, user }: any) {
    //     // user 객체와 user.email의 존재 여부를 확인
    //     if (!user || !user.email) {
    //       console.error('User data is incomplete:', user)
    //       throw new Error('User data is incomplete')
    //     }

    //     const params = {
    //       UserPoolId: process.env.COGNITO_USER_POOL_ID,
    //       Username: user.email,
    //     }
    //     const command = new AdminGetUserCommand(params)

    //     try {
    //       await cognitoClient.send(command)
    //       // 사용자가 존재하면 원래 URL로 리디렉션
    //       return url
    //     } catch (error: any) {
    //       if (error.name === 'UserNotFoundException') {
    //         // 사용자가 존재하지 않으면 /join 페이지로 리디렉션
    //         return `${baseUrl}/join`
    //       }
    //       console.error('Error validation user in Cognito: ', error)
    //       throw new Error('Internal Server Error')
    //     }
    //   },

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
  secret: process.env.NEXT_PUBLIC_SECRET,
}
