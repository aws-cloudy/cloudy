import { Hashtag } from '@prisma/client'

export interface IHashtag {
  id: number | null
  title: string
}

export interface IAnswer {
  id: number
  memberId: string
  memberName: string
  createdAt: string | Date
  desc: string
  questionId: number | null
}

export interface IQuestionDetail {
  id: string | number
  memberName: string
  memberId: string
  title: string
  desc: string
  hit: number
  createdAt: Date | string
  hashtags: { hashtag: Hashtag }[]
  authId?: string | undefined
}

export interface IQuestionAnswer {
  id: string | number
  answers: IAnswer[]
  checkedId: number | null
  authId?: string | undefined
  isWriter?: boolean
}

export interface IQuestion extends IQuestionDetail, IQuestionAnswer {}

export interface ICommunityListItem {
  id: number
  title: string
  desc: string
  createdAt: string
  memberName: string
  checkedId: number | null
  hashtags: { hashtag: IHashtag }[]
  _count: {
    answers: number
  }
}

export interface ICreateQuestion {
  tags: IHashtag[]
  title: string
  desc: string
  imageData: {
    path: string
    url: string
  }[]
}

export interface IUpdateQuestionAPI extends ICreateQuestion {
  questionId: string | number
  authorId: string
}

export interface IImage {
  localUrl: string
  file: File | Blob
}

export interface IUpdateQuestion {
  id?: number | string
  authorId?: string
  title?: string
  desc?: string
  hashtags?: Hashtag[]
}
