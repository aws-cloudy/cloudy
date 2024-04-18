import { render, screen } from '@testing-library/react'
import Header from '.'

describe('<Header />', () => {
  it('Header의 logo를 렌더링합니다', () => {
    render(<Header />)

    const logo = screen.getByAltText('logo')
    expect(logo).toBeInTheDocument()
  })

  it('Header의 메뉴를 렌더링합니다', () => {
    render(<Header />)
    const menuItems = screen.getAllByRole('link')
    expect(menuItems).toHaveLength(5)
    expect(menuItems[1]).toHaveTextContent('학습')
    expect(menuItems[2]).toHaveTextContent('로드맵')
    expect(menuItems[3]).toHaveTextContent('커뮤니티')
  })
})
