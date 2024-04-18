import type { Preview } from '@storybook/react'
import '@/styles/globals.scss'

// 미리보기 화면에 대한 설정 적용
const preview: Preview = {
  // parameters: 스토리에 대한 메타데이터 정보들
  // 주로 스토리북 feature & addon 설정
  parameters: {
    controls: {
      matchers: {
        color: /(background|color)$/i,
        date: /Date$/i,
      },
    },
  },
}

export default preview
