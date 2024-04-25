import { Dispatch, SetStateAction } from 'react'
import { IHashtag } from './community'

export interface IEditorHashtag {
  tags: IHashtag[]
  setTags: Dispatch<SetStateAction<IHashtag[]>>
}

export interface IEditorComponent {
  desc: string
  setDesc: Dispatch<SetStateAction<string>>
}
