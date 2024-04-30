import { Dispatch, SetStateAction } from 'react'

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

export interface ILearningSearchResult {
  keyword: string
}

export interface IJob {
  id: number
  name: string
}

export interface IService {
  id: number
  name: string
}

export interface ILearningCard {
  id: number
  title: string
  difficulty: string
  duration: string
  desc: string
  summary: string
  thumbnail: string
  link: string
  type: string
  job: IJob[]
  service: IService[]
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
