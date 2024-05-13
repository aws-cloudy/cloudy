import { Dispatch, SetStateAction } from 'react'
import { IFilter, ILearningCard } from './learning'

export interface IRoadmapFilterDropdown {
  job: IFilter
  setJob: Dispatch<SetStateAction<IFilter>>
  service: IFilter
  setService: Dispatch<SetStateAction<IFilter>>
}

export interface IRoadmapFilterInput {
  value: string
  setValue: Dispatch<SetStateAction<string>>
  onSearch: () => void
}

export interface IRoadmapCard {
  roadmapId: number
  commentsCnt: number
  job: string
  service: string
  summary: string
  thumbnail: string
  title: string
  isScrapped: boolean
  bookmarkId: number
  isUseMypage: boolean
}

export interface IMember {
  id: string
  name: string
}

export interface IComment {
  commentId: number
  member: IMember
  content: string
  regAt: string
}

export interface IRoadmapCardExtend extends IRoadmapCard {
  desc: string
}

export interface IRoadmapDetailData {
  data: {
    detail: IRoadmapCardExtend
    courses: ILearningCard[]
  }
  memberId: string | undefined
  comments: IComment[]
  bookmarkId?: number
}
