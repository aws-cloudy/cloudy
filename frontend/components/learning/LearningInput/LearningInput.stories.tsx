import type { Meta, StoryObj } from '@storybook/react'
import LearningInput from './'
import { fn } from '@storybook/test'

const meta = {
  title: 'learning/LearningInput',
  component: LearningInput,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: { value: 'value', setValue: fn(), keyword: 'keyword', setKeyword: fn() },
} satisfies Meta<typeof LearningInput>

export default meta

type Story = StoryObj<typeof meta>

export const Input: Story = {}
