import type { Meta, StoryObj } from '@storybook/react'
import Header from './'
import { SessionProvider } from 'next-auth/react'

const Component = () => (
  <SessionProvider session={null}>
    <Header />
  </SessionProvider>
)

const meta = {
  title: 'common/Header',
  component: Component,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
} satisfies Meta<typeof Header>

export default meta

type Story = StoryObj<typeof meta>

export const LoggedOut: Story = {}
