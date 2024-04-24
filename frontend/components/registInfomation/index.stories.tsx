import type { Meta, StoryObj } from '@storybook/react'
import RegistInfo from '.'

const meta = {
  title: 'components/RegistInfo',
  component: RegistInfo,
  parameters: {
    layout: 'fullscreen',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof RegistInfo>

export default meta

type Story = StoryObj<typeof meta>

export const Regist: Story = {}
