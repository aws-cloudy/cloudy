'use client'

import React, { useEffect, useRef } from 'react'
import styles from './Observer.module.scss'

function Observer({ callback }: { callback: IntersectionObserverCallback }) {
  const observerRef = useRef<HTMLDivElement>(null)

  useEffect(() => {
    if (!observerRef.current) return
    const observer = new IntersectionObserver(callback)
    observer.observe(observerRef.current)

    return () => {
      if (!observerRef.current) return
      return observer.unobserve(observerRef.current)
    }
  })

  return <div ref={observerRef} className={styles.container}></div>
}

export default Observer
