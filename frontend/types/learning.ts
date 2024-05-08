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

export interface IJob {
  id: number
  name: string
}

export interface IService {
  id: number
  name: string
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
