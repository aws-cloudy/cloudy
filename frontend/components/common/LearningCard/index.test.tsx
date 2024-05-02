import { ILearningCard } from '@/types/learning'
import { fireEvent, render, screen } from '@testing-library/react'
import LearningCard from '.'
import { getDifficulty } from '@/utils/getDifficulty'

describe('LearningCard', () => {
  const mockItem: ILearningCard = {
    learningId: 0,
    thumbnail: '/img/learning/1.png',
    title: 'test title',
    difficulty: 'Intermediate',
    duration: '2h 10m',
    summary: 'test summary',
    link: 'test link',
    serviceType: 'Digital Course',
  }

  it('더 알아보기 toggle이 잘 작동하는지 확인합니다', () => {
    render(<LearningCard item={mockItem} layout="grid" />)

    // 처음에 요약이 잘 숨겨져 있는지 확인
    expect(screen.queryByText('test summary')).not.toBeInTheDocument()
    // 더 알아보기 버튼을 클릭하면 요약이 나타나는지 확인
    fireEvent.click(screen.getByText('더 알아보기'))
    expect(screen.getByText('test summary')).toBeInTheDocument()
  })

  it('getDifficulty 함수가 잘 작동하는지 확인합니다', () => {
    const difficulty = getDifficulty('Intermediate')
    expect(difficulty.text).toBe('중급')
    expect(difficulty.class).toBe('intermediate')
  })
})
