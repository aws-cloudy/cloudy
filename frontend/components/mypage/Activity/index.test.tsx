import { render, screen } from '@testing-library/react'
import Activity from '.'

describe('<Activity/>', () => {
  it('활동내역 컴포넌트를 렌더링합니다.', () => {
    render(<Activity />)
  })
})
