import type { Meta, StoryObj } from '@storybook/react'
import LearningCard from './'
import { learningData } from '@/components/learning/LearningList/learningData'
import { ILearningCard } from '@/types/learning'

const LearningCardComponent = ({ item, layout }: { item: ILearningCard; layout: string }) => (
  <div style={{ width: layout === 'justify' ? '100%' : 250 }}>
    <LearningCard item={item} layout={layout} />
  </div>
)

const meta = {
  title: 'common/LearningCard',
  component: LearningCardComponent,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: { item: learningData[0], layout: 'grid' },
} satisfies Meta<typeof LearningCard>

export default meta

type Story = StoryObj<typeof meta>

export const GridItem: Story = {}

export const JustifyItem: Story = {
  args: { layout: 'justify' },
}
