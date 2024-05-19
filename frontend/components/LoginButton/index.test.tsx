import { render, screen } from '@testing-library/react'
import LoginButton from '.'

describe('<LoginButton />', () => {
  it('로그인 페이지의 LoginButton을 렌더링합니다', () => {
    // session의 값이 null인 경우, 로그인 버튼이 보인다.
    render(<LoginButton session={null} />)

    const google = screen.getByAltText('Google')
    const cognito = screen.getByAltText('Cognito')
    // 구글, 코그니토 로그인 버튼이 존재하는지 체크한다.
    expect(google).toBeInTheDocument()
    expect(cognito).toBeInTheDocument()
  })
})
