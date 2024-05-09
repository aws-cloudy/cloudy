import styles from './ChatRoomMessage.module.scss'
import { IMessage } from '@/types/chatbot'
import { useChatbotType } from '@/stores/chatbotStore'
import { chatBotList } from '@/constants/chatbot'
import Image from 'next/image'

function ChatRoomMessage({ sender, content, waiting }: IMessage) {
  const botType = useChatbotType()
  const icon = chatBotList[botType].image

  return (
    <div className={styles.container}>
      <div className={`${styles.inner} ${sender === 'cpu' ? styles.bot : styles.user}`}>
        {sender === 'cpu' && <Image src={icon} alt={botType} width={500} height={500} className={styles.botIcon} />}
        <div className={`${styles.message} ${sender === 'cpu' ? styles.messageBot : styles.messageUser}`}>
          {waiting ? (
            <Image src="/gif/spinner_transparent.gif" alt="loading" width={30} height={30} />
          ) : (
            <span>{content}</span>
          )}
        </div>
      </div>
    </div>
  )
}

export default ChatRoomMessage
