import React from 'react'
import styles from './Header.module.scss'
import Link from 'next/link'
import { MdOutlineLanguage } from 'react-icons/md'

const Header = () => {
  return (
    <header className={styles.header}>
      <div className={styles.headerWrap}>
        <Link href="/" className={styles.logoWrap}>
          <img className={styles.logo} src="/img/logo.png" alt="logo" />
        </Link>
        <div className={styles.menuList}>
          <Link href="/learning" className={styles.menuItem}>
            학습
          </Link>
          <Link href="/roadmap" className={styles.menuItem}>
            로드맵
          </Link>
          <Link href="/community" className={styles.menuItem}>
            커뮤니티
          </Link>
        </div>
        <div className={styles.RightWrap}>
          <Link href="/learning" className={styles.menuItem}>
            로그인
          </Link>
          <MdOutlineLanguage width="50px" size={24} className="languageIcon" />
        </div>
      </div>
    </header>
  )
}

export default Header
