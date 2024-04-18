import { render, screen } from '@testing-library/react'
import Header from '.'

describe('<Header />', () => {
  it('Header의 logo를 렌더링합니다', () => {
    render(<Header />)

    const logo = screen.getByAltText('logo')
    expect(logo).toBeInTheDocument()
  })
})
