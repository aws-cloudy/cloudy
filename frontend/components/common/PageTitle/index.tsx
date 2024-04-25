'use client'

import React from 'react'
import { usePathname } from 'next/navigation'
import { routeKr } from './routeData'

import styles from './PageTitle.module.scss'

function PageTitle() {
  const pathname = usePathname()
  const routes = routeKr[pathname]

  return (
    <div className={styles.container}>
      <div className={styles.route}>
        {routes.route.map((e, idx) => (
          <span key={idx} className={styles.routeItem}>
            {e} {idx !== routes.route.length - 1 && '>'}
          </span>
        ))}
      </div>
      <h1 className={styles.title}>{routes.title}</h1>
    </div>
  )
}

export default React.memo(PageTitle)
