import type { Meta, StoryObj } from '@storybook/react'
import Favorites from '.'

const meta = {
  title: 'components/Mypage/Favorites',
  component: Favorites,
  parameters: {
    layout: 'fullscreen',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof Favorites>

export default meta

type Story = StoryObj<typeof meta>

export const FavoritePage: Story = {}
