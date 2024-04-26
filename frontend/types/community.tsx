export interface IHashtag {
  id: number | null
  title: string
}

export interface IAnswer {
  id: number
  memberId: number
  memberName: string
  createdAt: string | Date
  desc: string
  questionId: number | null
}

export interface IQuestionDetail {
  id: string | number
  memberName: string
  memberId: number
  title: string
  desc: string
  hit: number
  createdAt: Date | string
  hashtags?: { hashtag: IHashtag }[]
}

export interface IQuestionAnswer {
  id: string | number
  answers?: IAnswer[]
  checkedId?: number | null
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
}
