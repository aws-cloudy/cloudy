import NextAuth from 'next-auth/next'
import GoogleProvider from 'next-auth/providers/google'

const handler = NextAuth({
  //Providers 소셜 로그인 서비스 코드
  providers: [
    GoogleProvider({
      clientId: process.env.GOOGLE_CLIENT_ID || '',
      clientSecret: process.env.GOOGLE_CLIENT_SECRET || '',
    }),
  ],
})

export { handler as GET, handler as POST }
