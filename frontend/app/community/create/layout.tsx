import Protected from '@/components/Protected'
import React from 'react'

export default function CommunityCreateLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return (
    <>
      {/* @ts-expect-error Async Server Component */}
      <Protected>{children}</Protected>
    </>
  )
}
