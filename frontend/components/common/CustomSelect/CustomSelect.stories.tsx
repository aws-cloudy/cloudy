import type { Meta, StoryObj } from '@storybook/react'
import CustomSelect from './'
import { fn } from '@storybook/test'
import { roadmapJobFilter } from '@/constants/roadmap'
import { ICustomSelect } from '@/types/common'

const CustomSelectComponent = ({ item, setItem, options }: ICustomSelect) => (
  <div style={{ width: 200 }}>
    <CustomSelect item={item} setItem={setItem} options={options} />
  </div>
)

const meta = {
  title: 'common/CustomSelect',
  component: CustomSelectComponent,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: { item: roadmapJobFilter[0], setItem: fn(), options: roadmapJobFilter },
} satisfies Meta<typeof CustomSelect>

export default meta

type Story = StoryObj<typeof meta>

export const Select: Story = {}
