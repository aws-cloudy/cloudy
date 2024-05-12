import React, { Dispatch, SetStateAction } from 'react'
import { IFilter } from './learning'

export interface IPageTitle {
  [name: string]: {
    title: string
    route: string[]
  }
}

export interface IButton extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  width?: string
}

export interface ICustomSelect {
  item: IFilter
  setItem: Dispatch<SetStateAction<IFilter>>
  options: IFilter[]
}

export interface ICustomInput {
  value: string
  setValue: Dispatch<SetStateAction<string>>
  width?: string
  onSearch: () => void
}
