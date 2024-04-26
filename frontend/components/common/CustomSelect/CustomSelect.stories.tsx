import type { Meta, StoryObj } from '@storybook/react'
import CustomSelect from './'

const CustomSelectComponent = () => (
  <div style={{ width: 200 }}>
    <CustomSelect />
  </div>
)

const meta = {
  title: 'common/CustomSelect',
  component: CustomSelectComponent,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: {},
} satisfies Meta<typeof CustomSelect>

export default meta

type Story = StoryObj<typeof meta>

export const Select: Story = {}
