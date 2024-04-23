import type { Meta, StoryObj } from '@storybook/react'
import LearningItem from './'
import { learningData } from '@/components/learning/LearningList/learningData'

const meta = {
  title: 'common/LearningItem',
  component: LearningItem,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: { item: learningData[0] },
} satisfies Meta<typeof LearningItem>

export default meta

type Story = StoryObj<typeof meta>

export const Item: Story = {}
