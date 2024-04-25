import { act, fireEvent, getByTestId, render, screen, waitFor } from '@testing-library/react'
import ChatRoomInput from '.'

describe('ChatbotInput', () => {
  test('메세지 전송 후 값 초기화 여부 확인', async () => {
    const mockSetMessages = jest.fn()

    const { getByTestId } = render(<ChatRoomInput setMessages={mockSetMessages} />)

    fireEvent.input(getByTestId('input'), {
      target: {
        value: 'test',
      },
    })

    fireEvent.submit(getByTestId('form'))
    await waitFor(() => expect(getByTestId('input')).toHaveValue(''))
  })

  test('빈 값을 보낼 경우 오류처리 여부를 확인합니다.', () => {
    const mockSetMessages = jest.fn()

    const { getByTestId, findByText } = render(<ChatRoomInput setMessages={mockSetMessages} />)

    const form = getByTestId('form')
    fireEvent.submit(form)

    findByText('이 입력란을 작성하세요')
  })
})
