import type { Meta, StoryObj } from '@storybook/react'
import RodmapCard from './'
import { roadmapData } from '@/components/roadmap/main/RoadmapListSection/roadmapData'
import { IRoadmapCard } from '@/types/roadmap'

const RodmapCardComponent = ({ item }: { item: IRoadmapCard }) => (
  <div style={{ width: 350 }}>
    <RodmapCard item={item} />
  </div>
)

const meta = {
  title: 'common/RodmapCard',
  component: RodmapCardComponent,
  parameters: {
    layout: 'fullscreen',
    nextjs: {
      appDirectory: true,
    },
  },

  tags: ['autodocs'],
  args: { item: roadmapData[0] },
} satisfies Meta<typeof RodmapCard>

export default meta

type Story = StoryObj<typeof meta>

export const Item: Story = {}
