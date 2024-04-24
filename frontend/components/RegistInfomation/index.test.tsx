import { render, screen } from '@testing-library/react'
import RegistInfomation from '.'

describe('<RegistInfo />', () => {
  it('로그인 후 RegistInfo를 렌더링합니다', () => {
    render(<RegistInfomation />)

    const selected = screen.queryByTestId('selected')

    selected?.click()
  })
})
