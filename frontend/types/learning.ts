import { Dispatch, SetStateAction } from 'react'

export interface ILearningInput {
  value: string
  setValue: Dispatch<SetStateAction<string>>
}

export interface ILearningSearchList {
  setValue: Dispatch<SetStateAction<string>>
}

export interface ILearningSearchListItem {
  onClick: (v: string) => void
}

export interface ILearningCard {
  learningId: number
  title: string
  difficulty: string
  duration: string
  summary: string | null
  thumbnail: string
  link: string
  serviceType: string
}

export interface ILearningFilterOpen {
  closeFilter: () => void
}

export interface ILearningFilterClose {
  openFilter: () => void
}

export interface IFilter {
  value: string
  name: string
  category: string
}

export interface ILearningFilter {
  job: IFilter[]
  service: IFilter[]
  type: IFilter[]
  difficulty: IFilter[]
}

export interface ILearningFilterToggle {
  title: string
  data: IFilter[]
  setFilter: (v: IFilter[]) => void
}

export interface ILearningFilterToggoleItem {
  item: IFilter
  list: IFilter[]
  setList: React.Dispatch<React.SetStateAction<IFilter[]>>
}
