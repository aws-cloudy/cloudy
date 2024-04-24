import Avatar from '@/components/common/Avatar'
import { IconType } from 'react-icons'
import styles from './ChatRoomMessage.module.scss'

type Props = {
  ico: IconType
  sender: 'cpu' | 'user'
  content: string
}

function ChatRoomMessage({ ico, sender, content }: Props) {
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
