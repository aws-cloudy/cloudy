import { Dispatch, SetStateAction } from 'react'
import { IconType } from 'react-icons'

export interface IChatBotList {
  [botType: string]: { sub: string; name: string; msg: string; image: string }
}

export interface IMessage {
  sender: 'cpu' | 'user'
  content: string
  waiting?: boolean
}

export interface IChat {
  id: number
  name: string
  content: string
  date: string
}

export interface IChatListItem {
  botType: 'cla' | 'oudy' | 'ama' | 'john'
}

export interface IChatRoomInput {
  handleConnect: (userMessage: string) => void
  setIsReceiving: React.Dispatch<SetStateAction<boolean>>
}
