import { IFilter } from '@/types/learning'
import { render } from '@testing-library/react'
import LearningTagListItem from '.'

describe('LearningTagListItem', () => {
  const mockItem: IFilter = {
    value: 'test',
    name: 'test',
    category: 'job',
  }

  it('태그 이름이 올바르게 렌더링됩니다', () => {
    const { getByText } = render(<LearningTagListItem item={mockItem} />)

    const filterName = getByText(mockItem.name)
    expect(filterName).toBeInTheDocument()
  })
})
