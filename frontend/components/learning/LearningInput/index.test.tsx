import { fireEvent, render, screen } from '@testing-library/react'
import LearningInput from '.'

describe('LearningInput', () => {
  it('input value가 변경되었을때 값이 올바르게 업데이트되는지 확인합니다', () => {
    const mockSetValue = jest.fn()
    const mockSetKeyword = jest.fn()

    render(<LearningInput value="" setValue={mockSetValue} keyword="" setKeyword={mockSetKeyword} />)

    const input = screen.getByPlaceholderText('무엇이든 검색해보세요 ...')
    fireEvent.change(input, { target: { value: 'test' } })

    expect(mockSetValue).toHaveBeenCalledWith('test')
    expect(mockSetKeyword).toHaveBeenCalledWith('')
  })

  it('Enter를 눌렀을때 handleSubmit 함수가 호출되는지 확인합니다', () => {
    const mockSetValue = jest.fn()
    const mockSetKeyword = jest.fn()

    render(<LearningInput value="test" setValue={mockSetValue} keyword="" setKeyword={mockSetKeyword} />)

    const input = screen.getByPlaceholderText('무엇이든 검색해보세요 ...')
    fireEvent.keyDown(input, { key: 'Enter', code: 'Enter' })

    expect(mockSetKeyword).toHaveBeenCalledWith('test')
  })
})
