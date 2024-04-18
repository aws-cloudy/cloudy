import type { Meta, StoryObj } from '@storybook/react'
import Header from './'

const meta = {
  title: 'common/Header',
  component: Header,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
} satisfies Meta<typeof Header>

export default meta

type Story = StoryObj<typeof meta>

export const LoggedOut: Story = {}
