import type { Meta, StoryObj } from '@storybook/react'
import Avatar from './'
import { AiFillAliwangwang } from 'react-icons/ai'

const meta = {
  title: 'common/Avatar',
  component: Avatar,
  parameters: {
    layout: 'fullscreen',
  },
  tags: ['autodocs'],
  args: {
    Icon: AiFillAliwangwang,
  },
} satisfies Meta<typeof Avatar>

export default meta

type Story = StoryObj<typeof meta>

export const Small: Story = {}
Small.args = { size: 'small' }

export const Mid: Story = {}
Mid.args = { size: 'mid' }

export const Big: Story = {}
Big.args = { size: 'big' }
