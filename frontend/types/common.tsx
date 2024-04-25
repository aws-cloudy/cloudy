import React from 'react'

export interface IPageTitle {
  [name: string]: {
    title: string
    route: string[]
  }
}

export interface IButton extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  width?: string
}
