import type { Meta, StoryObj } from '@storybook/react'
import CustomInput from './'
import { fn } from '@storybook/test'

const meta = {
  title: 'common/CustomInput',
  component: CustomInput,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  args: { value: '', setValue: fn(), onClick: fn() },
} satisfies Meta<typeof CustomInput>

export default meta

type Story = StoryObj<typeof meta>

export const EmptyInput: Story = {}

export const Input: Story = {
  args: { value: '클라우드', setValue: fn() },
}
