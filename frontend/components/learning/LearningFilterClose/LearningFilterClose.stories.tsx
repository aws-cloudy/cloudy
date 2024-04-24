import type { Meta, StoryObj } from '@storybook/react'
import LearningFilterClose from './'
import { fn } from '@storybook/test'

const meta = {
  title: 'learning/LearningFilterClose',
  component: LearningFilterClose,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: { openFilter: fn() },
} satisfies Meta<typeof LearningFilterClose>

export default meta

type Story = StoryObj<typeof meta>

export const Filter: Story = {}
