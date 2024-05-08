import { fireEvent, render, screen } from '@testing-library/react'
import LearningInput from '.'
import { useRouter, useSearchParams } from 'next/navigation'

jest.mock('next/navigation', () => ({
  useRouter: jest.fn(),
  useSearchParams: jest.fn(),
}))

describe('LearningInput', () => {
  beforeEach(() => {
    ;(useSearchParams as jest.Mock).mockReturnValueOnce({ get: () => '' })
  })

  it('입력 값이 변경될 때 setValue 함수가 호출되는지 확인', () => {
    const setValueMock = jest.fn()

    render(<LearningInput value="" setValue={setValueMock} />)

    const inputElement = screen.getByPlaceholderText('무엇이든 검색해보세요 ...')
    fireEvent.change(inputElement, { target: { value: 'test' } })
    expect(setValueMock).toHaveBeenCalledWith('test')
  })

  it('Enter 키가 눌렸을 때 handleSubmit 함수가 호출되는지 확인', () => {
    const pushMock = jest.fn()
    ;(useRouter as jest.Mock).mockReturnValueOnce({ push: pushMock })

    const setValueMock = jest.fn()
    render(<LearningInput value="test" setValue={setValueMock} />)

    const inputElement = screen.getByPlaceholderText('무엇이든 검색해보세요 ...')
    fireEvent.keyDown(inputElement, { key: 'Enter', code: 'Enter' })
    expect(pushMock).toHaveBeenCalledWith('/learning?query=test')
  })
})
