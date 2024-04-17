import type { Meta, StoryObj } from '@storybook/react'
import { fn } from '@storybook/test'
import Button from './Button'

// 메타 데이터, 제네릭에 Button 컴포넌트의 타입
const meta = {
  // 카테고리 설정
  title: 'Example/Book',
  // 컴포넌트 세팅
  component: Button,
  // 컴포넌트에 대한 문서를 자동으로 생성
  tags: ['autodocs'],
  argTypes: {
    // storybook 컨트롤 패널에서 색상 변경이 가능
    // backgroundColor: { control: 'color' },
  },
  args: {
    //fn : 테스트 유틸리티, 이벤트 핸들러 or 콜백 함수 시뮬레이션시 사용
    onClick: fn(),
  },
} satisfies Meta<typeof Button>

export default meta

// 스토리 타입, StoryObj의 제네릭에 컴포넌트 타입을 넘겨줌
type Story = StoryObj<typeof Button>

// 하나의 스토리 -> named export 해줌
// 스토리 이름도 카테고리에 표시
export const Primary: Story = {
  // 컴포넌트에 필요한 arguments, 리액트 컴포넌트에게 Props
  args: {
    variant: 'primary',
    children: '프라이머리',
  },
}

// 다른 스토리 (Dashed)
export const Dashed: Story = {
  args: {
    variant: 'dashed',
    children: '대쉬드',
  },
}

// Primary 스토리 args값을 재사용도 가능
export const Disabled: Story = {
  args: {
    ...Primary.args,
    children: 'disabled',
    disabled: true,
  },
}
