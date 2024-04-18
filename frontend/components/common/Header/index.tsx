'use client'

import React, { useState } from 'react'
import styles from './Header.module.scss'
import Link from 'next/link'
import { MdOutlineLanguage } from 'react-icons/md'
import { MdMenu } from 'react-icons/md'

const Header = () => {
  const [isOpen, setIsOpen] = useState(false)

  return (
    <>
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
            <MdOutlineLanguage size={24} className="languageIcon" />
          </div>
        </div>
      </header>
      {/* mobile */}
      <>
        <header className={styles.headerMobile}>
          <div className={styles.headerMobileWrap}>
            <Link href="/" className={styles.logoWrap}>
              <img className={styles.logo} src="/img/logo.png" alt="logo-mobile" />
            </Link>
            <div className={styles.MenuIconWrap} onClick={() => setIsOpen(!isOpen)}>
              <MdMenu size={24} />
            </div>
          </div>
          {isOpen && (
            <div className={styles.mobileMenuList} role="presentation">
              <Link href="/learning" className={styles.mobileMenuItem}>
                학습
              </Link>
              <Link href="/roadmap" className={styles.mobileMenuItem}>
                로드맵
              </Link>
              <Link href="/community" className={styles.mobileMenuItem}>
                커뮤니티
              </Link>
              <Link href="/community" className={styles.mobileMenuItem}>
                로그인
              </Link>
            </div>
          )}
        </header>
      </>
    </>
  )
}

export default Header
