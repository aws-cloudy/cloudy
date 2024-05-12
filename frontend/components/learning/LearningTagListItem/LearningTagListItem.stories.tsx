import type { Meta, StoryObj } from '@storybook/react'
import LearningTagListItem from './'

const meta = {
  title: 'learning/LearningTagListItem',
  component: LearningTagListItem,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: { item: { category: 'job', name: ' test', value: 'test' }, onClick: jest.fn() },
} satisfies Meta<typeof LearningTagListItem>

export default meta

type Story = StoryObj<typeof meta>

export const Item: Story = {}
