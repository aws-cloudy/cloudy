import type { StorybookConfig } from '@storybook/nextjs'
import path from 'path'

// Storybook에 대한 전반적인 설정 적용
const config: StorybookConfig = {
  // Storybook에 사용할 .mdx, .stories 파일 위치
  stories: ['../**/*.stories.mdx', '../**/*.stories.@(js|jsx|mjs|ts|tsx)'],
  addons: [
    '@storybook/addon-onboarding',
    '@storybook/addon-links',
    '@storybook/addon-essentials',
    '@chromatic-com/storybook',
    '@storybook/addon-interactions',
  ],
  framework: {
    name: '@storybook/nextjs',
    options: {},
  },
  docs: {
    autodocs: 'tag',
  },
  staticDirs: [path.join(__dirname, '..', 'public')],
}
export default config
