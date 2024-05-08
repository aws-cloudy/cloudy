import type { Meta, StoryObj } from '@storybook/react'
import CustomSelect from './'
import { fn } from '@storybook/test'
import { roadmapJobFilter } from '@/constants/roadmap'
import { ICustomSelect } from '@/types/common'
import RoadmapFilterSection from './'

// const CustomSelectComponent = ({ item, setItem, options }: ICustomSelect) => (
//   <div style={{ width: 200 }}>
//     <CustomSelect item={item} setItem={setItem} options={options} />
//   </div>
// )
const globalState = {
  keyword: 'ha',
}

const meta = {
  title: 'roadmap/로드맵 필터 및 검색',
  component: RoadmapFilterSection,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: {},
} satisfies Meta<typeof RoadmapFilterSection>

export default meta

type Story = StoryObj<typeof meta>

export const Select: Story = {}
