import type { Meta, StoryObj } from '@storybook/react'
import LearningInput from './'

const meta = {
  title: 'learning/LearningInput',
  component: LearningInput,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
} satisfies Meta<typeof LearningInput>

export default meta

type Story = StoryObj<typeof meta>

export const Input: Story = {}
