import { fireEvent, render, screen } from '@testing-library/react'
import LearningFilterToggoleItem from '.'
import { IFilter } from '@/types/learning'
import { useSetFilter } from '@/hooks/useSetFilter'
import { usejobFilter, useServiceFilter, useTypeFilter, useDifficultyFilter } from '@/stores/learning'

jest.mock('@/stores/learning', () => ({
  usejobFilter: jest.fn(),
  useServiceFilter: jest.fn(),
  useTypeFilter: jest.fn(),
  useDifficultyFilter: jest.fn(),
}))

jest.mock('@/hooks/useSetFilter', () => ({
  useSetFilter: jest.fn(),
}))

describe('LearningFilterToggoleItem', () => {
  const mockItem: IFilter = { name: 'Test Filter', value: 'test-filter', category: 'job' }

  beforeEach(() => {
    ;(usejobFilter as jest.Mock).mockReturnValue([])
    ;(useServiceFilter as jest.Mock).mockReturnValue([])
    ;(useTypeFilter as jest.Mock).mockReturnValue([])
    ;(useDifficultyFilter as jest.Mock).mockReturnValue([])
    ;(usejobFilter as jest.Mock).mockReturnValue([])
    ;(useSetFilter as jest.Mock).mockReturnValue(() => {})
  })

  // it('필터 항목을 클릭하면 setFilter 함수가 실행됩니다', () => {
  //   const mockSetFilter = jest.fn()
  //   ;(useSetFilter as jest.Mock).mockReturnValue(mockSetFilter)

  //   render(<LearningFilterToggoleItem item={mockItem} />)
  //   fireEvent.click(screen.getByText(mockItem.name))

  //   expect(mockSetFilter).toHaveBeenCalled()
  // })

  // it('필터가 선택되지 않았을 때 plus icon을 표시합니다', () => {
  //   render(<LearningFilterToggoleItem item={mockItem} />)
  //   expect(screen.getByTestId('plus-icon')).toBeInTheDocument()
  // })

  // it('필터가 선택되었을 때 minus icon을 표시합니다', () => {
  //   ;(usejobFilter as jest.Mock).mockReturnValue([mockItem])
  //   render(<LearningFilterToggoleItem item={mockItem} />)
  //   expect(screen.getByTestId('minus-icon')).toBeInTheDocument()
  // })
})
