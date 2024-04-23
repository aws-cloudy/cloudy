import type { Meta, StoryObj } from '@storybook/react'
import LoginButton from '.'

const meta = {
  title: 'components/LoginButton',
  component: LoginButton,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
} satisfies Meta<typeof LoginButton>

export default meta

type Story = StoryObj<typeof meta>

export const Login: Story = {}
