import type { Meta, StoryObj } from '@storybook/react'
import LearningItem from './'
import { learningData } from '@/components/learning/LearningList/learningData'
import { ILearningItem } from '@/types/learning'

const LearningItemComponent = ({ item, layout }: { item: ILearningItem; layout: string }) => (
  <div style={{ width: layout === 'justify' ? '100%' : 250 }}>
    <LearningItem item={item} layout={layout} />
  </div>
)

const meta = {
  title: 'common/LearningItem',
  component: LearningItemComponent,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: { item: learningData[0], layout: 'grid' },
} satisfies Meta<typeof LearningItem>

export default meta

type Story = StoryObj<typeof meta>

export const GridItem: Story = {}

export const JustifyItem: Story = {
  args: { layout: 'justify' },
}
