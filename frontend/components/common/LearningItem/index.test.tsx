import { ILearningItem } from '@/types/learning'
import { fireEvent, render, screen } from '@testing-library/react'
import LearningItem from '.'
import { getDifficulty } from '@/utils/getDifficulty'

describe('LearningItem', () => {
  const mockItem: ILearningItem = {
    id: 0,
    thumbnail: 'test image',
    title: 'test title',
    service: [{ id: 0, name: 'service 1' }],
    difficulty: 'Intermediate',
    duration: '2h 10m',
    summary: 'test summary',
    link: 'test link',
    job: [{ id: 0, name: 'job 1' }],
    desc: 'test desc',
    type: 'Digital Course',
  }

  it('더 알아보기 toggle이 잘 작동하는지 확인합니다', () => {
    render(<LearningItem item={mockItem} layout="grid" />)

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
