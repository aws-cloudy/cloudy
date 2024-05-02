import { Dispatch, SetStateAction } from 'react'
import { IAnswer, IHashtag, IImage } from './community'
import { Editor } from '@toast-ui/react-editor'

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
  author: string
  createdAt: string | Date
  authorId: string
  authId: string | undefined
}

export interface IDetailAnswerItem {
  ans: IAnswer
  isChecked: boolean
  authId: string | undefined
  isWriter?: boolean
}

export interface IEditorBody {
  editorRef: React.RefObject<Editor>
  setImages: React.Dispatch<React.SetStateAction<IImage[]>>
  orig?: string
}
