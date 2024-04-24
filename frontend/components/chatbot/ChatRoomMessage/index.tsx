import Avatar from '@/components/common/Avatar'
import styles from './ChatRoomMessage.module.scss'
import { IChatRoomMessage } from '@/types/chatbot'

function ChatRoomMessage({ ico, sender, content }: IChatRoomMessage) {
  return (
    <div className={styles.container}>
      <div className={`${styles.inner} ${sender === 'cpu' ? styles.bot : styles.user}`}>
        {sender === 'cpu' && <Avatar Icon={ico} />}
        <div className={`${styles.message} ${sender === 'cpu' ? styles.messageBot : styles.messageUser}`}>
          {content}
        </div>
      </div>
    </div>
  )
}

export default ChatRoomMessage
