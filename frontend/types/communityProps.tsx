import { Dispatch, SetStateAction } from 'react'
import { IAnswer, IHashtag } from './community'

export interface IEditorHashtag {
  tags: IHashtag[]
  setTags: Dispatch<SetStateAction<IHashtag[]>>
}

export interface IEditorComponent {
  desc: string
  setDesc: Dispatch<SetStateAction<string>>
}

export interface ICommunityDetailPage {
  params: {
    id: string
  }
}

export interface IDetailContentDesc {
  desc: string
}

export interface IDetailContentSubtitle {
  id: string | number
  user: string
  createdAt: string | Date
}

export interface IDetailAnswerItem {
  ans: IAnswer
  isChecked: boolean
}
