import { render, screen } from '@testing-library/react'
import Header from '.'
import { SessionProvider } from 'next-auth/react'

// 세션 데이터를 위한 가짜 객체
const mockSession = {
  expires: '1',
  user: {
    name: '테스트용',
    email: 'user@example.com',
  },
}

window.scrollTo = jest.fn()

describe('<Header />', () => {
  it('Header의 logo를 렌더링합니다', () => {
    render(
      <SessionProvider session={null}>
        <Header />
      </SessionProvider>,
    )

    const logo = screen.getByAltText('logo')
    expect(logo).toBeInTheDocument()
  })
})

afterEach(() => {
  jest.resetAllMocks()
})
afterAll(() => {
  jest.clearAllMocks()
})
