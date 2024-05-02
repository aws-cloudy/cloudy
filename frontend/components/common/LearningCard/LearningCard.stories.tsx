import type { Meta, StoryObj } from '@storybook/react'
import LearningCard from './'
import { learningData } from '@/components/learning/LearningList/learningData'
import { ILearningCard } from '@/types/learning'

const mockItem: ILearningCard = {
  learningId: 0,
  thumbnail: '/img/learning/1.png',
  title: 'test title',
  difficulty: 'Intermediate',
  duration: '2h 10m',
  summary: 'test summary',
  link: 'test link',
  serviceType: 'Digital Course',
}

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
  args: { item: mockItem, layout: 'grid' },
} satisfies Meta<typeof LearningCard>

export default meta

type Story = StoryObj<typeof meta>

export const GridItem: Story = {}

export const JustifyItem: Story = {
  args: { layout: 'justify' },
}
