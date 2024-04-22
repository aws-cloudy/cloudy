'use client'

import React, { useEffect, useRef, useState } from 'react'
import styles from './Header.module.scss'
import mainStyles from './HeaderMain.module.scss'
import Link from 'next/link'
import { MdOutlineLanguage } from 'react-icons/md'
import { MdMenu } from 'react-icons/md'
import { usePathname } from 'next/navigation'

const Header = () => {
  const [isOpen, setIsOpen] = useState(false)
  const navRef = useRef<HTMLDivElement>(null)
  const pathname = usePathname()
  const isMain = pathname === '/'
  const [isDark, setIsDark] = useState(isMain && window.scrollY < window.innerHeight - 80 ? true : false)
  const [style, setStyle] = useState(isDark ? mainStyles : styles)

  const scrollHandler = () => {
    if (isMain && window.scrollY < window.innerHeight - 80) {
      setIsDark(true)
      setStyle(mainStyles)
    } else {
      setIsDark(false)
      setStyle(styles)
    }
  }

  useEffect(() => {
    if (typeof window !== 'undefined') {
      if (isMain) {
        window.addEventListener('scroll', scrollHandler)
        return () => window.removeEventListener('scroll', scrollHandler)
      } else {
        setIsDark(false)
        setStyle(styles)
      }
    }
  }, [isMain])

  return (
    <>
      <header className={style.header} ref={navRef}>
        <div className={style.headerWrap}>
          <Link href="/" className={styles.logoWrap}>
            <img className={style.logo} src={isDark ? '/img/logo-white.png' : '/img/logo.png'} alt="logo" />
          </Link>
          <div className={style.menuList}>
            <Link href="/learning" className={style.menuItem}>
              학습
            </Link>
            <Link href="/roadmap" className={style.menuItem}>
              로드맵
            </Link>
            <Link href="/community" className={style.menuItem}>
              커뮤니티
            </Link>
          </div>
          <div className={style.RightWrap}>
            <Link href="/login" className={style.menuItem}>
              로그인
            </Link>
            <MdOutlineLanguage size={24} className="languageIcon" color={isDark ? '#fff' : ' #000'} />
          </div>
        </div>
      </header>
      {/* mobile */}
      <>
        <header className={style.headerMobile}>
          <div className={style.headerMobileWrap}>
            <Link href="/" className={style.logoWrap}>
              <img className={style.logo} src={isDark ? '/img/logo-white.png' : '/img/logo.png'} alt="logo-mobile" />
            </Link>
            <div className={style.MenuIconWrap} onClick={() => setIsOpen(!isOpen)}>
              <MdMenu size={24} color={isDark ? '#fff' : ' #000'} />
            </div>
          </div>
          {isOpen && (
            <div className={style.mobileMenuList} role="presentation">
              <Link href="/learning" className={style.mobileMenuItem}>
                학습
              </Link>
              <Link href="/roadmap" className={style.mobileMenuItem}>
                로드맵
              </Link>
              <Link href="/community" className={style.mobileMenuItem}>
                커뮤니티
              </Link>
              <Link href="/community" className={style.mobileMenuItem}>
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
