import { IChatListItem } from '@/types/chatbot'
import styles from './ChatListItem.module.scss'

function ChatListItem({ chat }: IChatListItem) {
  const { name, date, content } = chat

  return (
    <div className={styles.container}>
      <div className={styles.titleBox}>
        <p>{name}</p>
        <p>{date}</p>
      </div>
      <div className={styles.content}>
        <p>{content}</p>
      </div>
    </div>
  )
}

export default ChatListItem
