import type { Meta, StoryObj } from '@storybook/react'
import PageTitle from './'

const meta = {
  title: 'common/PageTitle',
  component: PageTitle,
  parameters: {
    layout: 'fullscreen',
    nextjs: {
      appDirectory: true,
      navigation: {
        pathname: '/community',
      },
    },
  },
  tags: ['autodocs'],
} satisfies Meta<typeof PageTitle>

export default meta

type Story = StoryObj<typeof meta>

export const PageTitleSample: Story = {}
