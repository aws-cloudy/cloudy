import styles from './ChatListItem.module.scss'

type Props = {
  chat: {
    id: number
    name: string
    content: string
    date: string
  }
}

function ChatListItem({ chat }: Props) {
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
