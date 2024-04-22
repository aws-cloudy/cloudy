import { Dispatch, SetStateAction } from 'react'

export interface ILearningInput {
  value: string
  setValue: Dispatch<SetStateAction<string>>
  setKeyword: Dispatch<SetStateAction<string>>
}
