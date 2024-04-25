'use client'

import React, { useEffect, useRef, useState } from 'react'
import styles from './Header.module.scss'
import mainStyles from './HeaderMain.module.scss'
import Link from 'next/link'
import { MdOutlineLanguage } from 'react-icons/md'
import { MdMenu } from 'react-icons/md'
import { usePathname } from 'next/navigation'

const Header = ({ session }: any) => {
  const [isOpen, setIsOpen] = useState(false)
  const navRef = useRef<HTMLDivElement>(null)
  const pathname = usePathname()
  const isMain = pathname === '/'
  const [isDark, setIsDark] = useState(isMain ? true : false)
  const [style, setStyle] = useState(isDark ? mainStyles : styles)

  const scrollHandler = () => {
    if (typeof window !== 'undefined') {
      if (isMain && window.scrollY < window.innerHeight - 80) {
        setIsDark(true)
      } else {
        setIsDark(false)
      }
    }
  }

  useEffect(() => {
    const { scrollY, innerHeight, scrollTo } = window
    setIsDark(isMain && scrollY < innerHeight - 80 ? true : false)
    scrollTo(0, 0)

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

  useEffect(() => {
    if (isDark) {
      setStyle(mainStyles)
    } else {
      setStyle(styles)
    }
  }, [isDark])

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
            {session ? (
              <Link href="/mypage" className={style.menuItem}>
                {session.user?.name}님
              </Link> // 세션에 사용자 이름이 있다면 표시
            ) : (
              <Link href="/login" className={style.menuItem}>
                로그인
              </Link>
            )}
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
