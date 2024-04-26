import React from 'react'
import styles from './Layout.module.scss'

const Layout = ({ children }: { children: React.ReactNode }) => {
  return <section className={styles.section}>{children}</section>
}

export default Layout
