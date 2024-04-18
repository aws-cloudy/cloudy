import type { Meta, StoryObj } from '@storybook/react'
import { fn } from '@storybook/test'
import Header from './'

const meta = {
  title: 'common/Header',
  component: Header,
  parameters: {
    layout: 'fullscreen',
  },

  tags: ['autodocs'],
  argTypes: {},

  args: { onClick: fn() },
} satisfies Meta<typeof Header>

export default meta
type Story = StoryObj<typeof meta>

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const Primary: Story = {
  args: {
    primary: true,
    label: 'Button',
  },
}
