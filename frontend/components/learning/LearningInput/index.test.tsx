import { fireEvent, render, screen } from '@testing-library/react'
import LearningInput from '.'
import { useLearningActions } from '@/stores/learning'

jest.mock('@/stores/learning', () => ({
  useLearningActions: jest.fn(),
  useLearningKeyword: () => '',
}))

describe('LearningInput', () => {
  let mockSetKeyword
  beforeEach(() => {
    // beforeEach 블록에서 모든 테스트 전에 mock 함수를 설정
    mockSetKeyword = jest.fn()
    ;(useLearningActions as jest.Mock).mockReturnValue({ setKeyword: mockSetKeyword })
  })

  it('input value가 변경되었을때 값이 올바르게 업데이트되는지 확인합니다', () => {
    const mockSetValue = jest.fn()

    render(<LearningInput value="" setValue={mockSetValue} />)

    const input = screen.getByPlaceholderText('무엇이든 검색해보세요 ...')
    fireEvent.change(input, { target: { value: 'test' } })

    expect(mockSetValue).toHaveBeenCalledWith('test')
  })

  it('Enter를 눌렀을때 handleSubmit 함수가 호출되는지 확인합니다', () => {
    const mockSetValue = jest.fn()

    const { setKeyword } = useLearningActions()

    render(<LearningInput value="test" setValue={mockSetValue} />)

    const input = screen.getByPlaceholderText('무엇이든 검색해보세요 ...')
    fireEvent.keyDown(input, { key: 'Enter', code: 'Enter' })

    expect(setKeyword).toHaveBeenCalledWith('test')
  })
})
