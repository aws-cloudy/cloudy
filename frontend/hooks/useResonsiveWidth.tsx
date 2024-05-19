import { useEffect, useState } from 'react'
import { useMediaQuery } from 'react-responsive'

// 브라우저의 뷰포트 확인
export const useResponsiveWidth = () => {
  const [isTablet, setIsTablet] = useState(false)

  const isTabletScreen = useMediaQuery({ query: '(max-width: 992px)' })

  useEffect(() => {
    setIsTablet(isTabletScreen)
  }, [isTabletScreen])

  return { isTablet }
}
