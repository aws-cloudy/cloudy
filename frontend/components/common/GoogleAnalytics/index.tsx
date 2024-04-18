'use client'

import { useEffect } from 'react'
import { initializeGA } from '@/utils/common/GoogleAnalytics'

declare global {
  interface Window {
    GA_INITIALIZED: any
  }
}

export default function GoogleAnalytics() {
  useEffect(() => {
    if (!window.GA_INITIALIZED) {
      initializeGA()
      window.GA_INITIALIZED = true
    }
  }, [])
  return <></>
}
