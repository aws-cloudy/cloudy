import { Dispatch, MouseEventHandler, SetStateAction } from 'react'

export interface ILearningInput {
  value: string
  setValue: Dispatch<SetStateAction<string>>
  keyword: string
  setKeyword: Dispatch<SetStateAction<string>>
}

export interface ILearningSearchList {
  setValue: Dispatch<SetStateAction<string>>
  setKeyword: Dispatch<SetStateAction<string>>
}

export interface ILearningSearchListItem {
  onClick: (v: string) => void
}
