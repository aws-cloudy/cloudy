import type { Meta, StoryObj } from '@storybook/react'
import MainSearch from '.'

const meta: Meta<typeof MainSearch> = {
  title: 'main/MainSearch',
  component: MainSearch,
  tags: ['autodocs'],
}

export default meta

type Story = StoryObj<typeof meta>

export const Default: Story = {}
