import { Dispatch, SetStateAction } from 'react'
import { IFilter } from './learning'

export interface IRoadmapFilterDropdown {
  job: IFilter
  setJob: Dispatch<SetStateAction<IFilter>>
  service: IFilter
  setService: Dispatch<SetStateAction<IFilter>>
}

export interface IRoadmapFilterInput {
  value: string
  setValue: Dispatch<SetStateAction<string>>
}

export interface IRoadmapCard {
  id: number
  image: string
  status: string
  title: string
  context: string
  tags: string[]
  comments: number
}
