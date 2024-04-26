export interface IHashtag {
  id: number | null
  title: string
}

export interface IAnswer {
  id: number
  memberId: number
  memberName: string
  createdAt: string
  desc: string
  questionId: number
}

export interface IQuestion {
  id: number
  memberId: number
  memberName: string
  title: string
  desc: string
  hit: number
  createdAt: Date | string
  checkedId?: number | null
  hashtags?: IHashtag[]
  answers?: IAnswer[]
}

export interface ICommunityListItem {
  id: number
  title: string
  desc: string
  createdAt: string
  memberName: string
  checkedId: number | null
  hashtags: { hashtag: IHashtag }[]
  answers: number
}

export interface ICreateQuestion {
  tags: IHashtag[]
  title: string
  desc: string
}
