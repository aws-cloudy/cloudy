import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import GoogleAnalytics from '@/components/common/GoogleAnalytics'
import '../styles/globals.scss'
import Header from '@/components/common/Header'
import Auth from '@/context/auth'
import ChatBotIcon from '@/components/chatbot/ChatBotIcon'
import ChatBot from '@/components/chatbot/ChatBot'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Cloudy',
  description: 'AWS 클라우드 서비스를 친구처럼 편안하게 Cloudy',
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <Auth>
          <GoogleAnalytics />
          <Header />
          <ChatBot />
          <ChatBotIcon />
          <div style={{ paddingTop: 80 }}>{children}</div>
        </Auth>
      </body>
    </html>
  )
}
