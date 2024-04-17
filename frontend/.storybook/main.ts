import type { StorybookConfig } from '@storybook/nextjs'

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
  staticDirs: ['..\\public'],
}
export default config
