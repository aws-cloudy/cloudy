import { render, screen } from '@testing-library/react'
import Header from '.'

describe('<Header />', () => {
  it('Header를 렌더링합니다', () => {
    const { container } = render(<Header />)

    const header = screen.getByText('Header')

    expect(header).toBeInTheDocument()
    expect(container).toMatchSnapshot()
  })
})
