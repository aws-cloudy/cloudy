import { IRoadmapCard } from '@/types/roadmap'
import { fireEvent, render, screen } from '@testing-library/react'
import RoadmapCard from '.'

describe('RoadmapCard', () => {
  const mockItem: IRoadmapCard = {
    roadmapId: 1,
    thumbnail: '/img/roadmap/roadmapgreen.jpg',
    title: 'AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide AWS Skill Builder Learner Guide Guide ',
    summary:
      '효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 알 수 있음 효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 보여준다.',
    job: 'Developer',
    service: 'Develoling',
    commentsCnt: 2,
  }

  it('북마크 기능이 정상 작동됩니다', () => {
    render(<RoadmapCard item={mockItem} />)
    const BookmarkButton = screen.getByRole('button')
    // 북마크 되어있을 경우 scrap-icon
    expect(screen.getByTestId('scrap-icon')).toBeInTheDocument()
    fireEvent.click(BookmarkButton)
    // 북마크 해제 되었을 경우 unscrap-icon
    expect(screen.getByTestId('unscrap-icon')).toBeInTheDocument()
  })
})
