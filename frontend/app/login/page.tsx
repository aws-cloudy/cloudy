'use client'
import Navbar from '@/components/Navbar'
import { signIn, useSession } from 'next-auth/react'
import { useRouter } from 'next/router'

export default function LoginPage() {
  //   const { data: session } = useSession()
  //   const router = useRouter()

  return (
    <div>
      <Navbar />
    </div>
  )
}
