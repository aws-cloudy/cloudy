import React, { ReactNode } from 'react'
import styles from './Layout.module.scss'

const Layout = ({ children }: { children: ReactNode }) => {
  return <section className={styles.section}>{children}</section>
}

export default Layout
