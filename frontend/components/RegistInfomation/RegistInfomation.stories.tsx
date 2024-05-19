import type { Meta, StoryObj } from '@storybook/react'
import RegistInfomation from '.'

const meta = {
  title: 'components/RegistInfomation',
  component: RegistInfomation,
  parameters: {
    layout: 'fullscreen',
  },
  tags: ['autodocs'],
  args: { username: '' },
} satisfies Meta<typeof RegistInfomation>

export default meta

type Story = StoryObj<typeof meta>

export const Regist: Story = {}
