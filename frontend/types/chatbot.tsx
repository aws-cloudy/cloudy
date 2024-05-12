import { Dispatch, SetStateAction } from 'react'
import { IconType } from 'react-icons'

export interface IChatBotList {
  [botType: string]: { sub: string; name: string; msg: string; image: string; type: number }
}

export interface IMessage {
  isUserSent: boolean
  content: string
  regAt: string | null
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
