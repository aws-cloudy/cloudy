import type { Meta, StoryObj } from '@storybook/react'
import RoadmapCard from '.'

const meta = {
  title: 'components/common/RoadmapCard',
  component: RoadmapCard,
  parameters: {
    layout: 'fullscreen',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof RoadmapCard>

export default meta

type Story = StoryObj<typeof meta>

export const Roadmap: Story = {}
