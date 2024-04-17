import type { Preview } from '@storybook/react'
import '../app/globals.scss'

// 미리보기 화면에 대한 설정 적용
const preview: Preview = {
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
