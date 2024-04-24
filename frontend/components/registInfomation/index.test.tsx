import { render, screen } from '@testing-library/react'
import RegistInfo from '.'

describe('<RegistInfo />', () => {
  it('로그인 후 RegistInfo를 렌더링합니다', () => {
    render(<RegistInfo />)

    const selected = screen.queryByTestId('selected')

    selected?.click()
  })
})
