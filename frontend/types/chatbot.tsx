import { Dispatch, SetStateAction } from 'react'
import { IconType } from 'react-icons'

export interface IMessage {
  sender: 'cpu' | 'user'
  content: string
}

export interface IChat {
  id: number
  name: string
  content: string
  date: string
}

export interface IChatRoomInput {
  setMessages: Dispatch<SetStateAction<IMessage[]>>
}

export interface IChatListItem {
  chat: IChat
}

export interface IChatRoomMessage extends IMessage {
  ico: IconType
}
