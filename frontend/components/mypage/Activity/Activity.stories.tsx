import type { Meta, StoryObj } from '@storybook/react'
import Activity from '.'

const meta = {
  title: 'components/Mypage/Activity',
  component: Activity,
  parameters: {
    layout: 'fullscreen',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof Activity>

export default meta

type Story = StoryObj<typeof meta>

export const ActivityPage: Story = {}
