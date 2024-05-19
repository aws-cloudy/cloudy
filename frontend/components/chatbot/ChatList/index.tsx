import ChatListItem from '../ChatListItem'
import styles from './ChatList.module.scss'

function ChatList() {
  const botTypes: ('cla' | 'oudy' | 'ama' | 'john')[] = ['cla', 'oudy', 'ama', 'john']

  return (
    <div className={styles.container}>
      {botTypes.map((each, idx) => (
        <ChatListItem botType={each} key={idx} />
      ))}
    </div>
  )
}

export default ChatList
