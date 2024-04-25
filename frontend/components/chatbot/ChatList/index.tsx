import ChatListItem from '../ChatListItem'
import styles from './ChatList.module.scss'

const dummy = [
  { id: 1, name: '우디', content: '마지막으로 주고받은 메세지1', date: '2024-04-23' },
  { id: 2, name: '클라', content: '마지막으로 주고받은 메세지2', date: '2024-04-23' },
  { id: 3, name: '클라', content: '마지막으로 주고받은 메세지3', date: '2024-04-22' },
  { id: 4, name: '우디', content: '마지막으로 주고받은 메세지4', date: '2024-04-21' },
  { id: 5, name: '우디', content: '마지막으로 주고받은 메세지5', date: '2024-04-21' },
  { id: 6, name: '우디', content: '마지막으로 주고받은 메세지6', date: '2024-04-21' },
  { id: 7, name: '우디', content: '마지막으로 주고받은 메세지7', date: '2024-04-21' },
  { id: 8, name: '우디', content: '마지막으로 주고받은 메세지8', date: '2024-04-21' },
]

function ChatList() {
  return (
    <div className={styles.container}>
      <div className={styles.chatContainer}>
        {dummy.map(e => (
          <ChatListItem key={e.id} chat={e} />
        ))}
      </div>
    </div>
  )
}

export default ChatList
