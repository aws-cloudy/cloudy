import type { Meta, StoryObj } from '@storybook/react'
import Hazard from './'

const meta = {
  title: 'common/Hazard',
  component: Hazard,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
} satisfies Meta<typeof Hazard>

export default meta

type Story = StoryObj<typeof meta>

export const Item: Story = {}
