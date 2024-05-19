import { chatBotList } from '@/constants/chatbot'
import { IChatListItem } from '@/types/chatbot'
import styles from './ChatListItem.module.scss'
import Image from 'next/image'
import { useChatbotActions } from '@/stores/chatbotStore'

function ChatListItem({ botType }: IChatListItem) {
  const { sub, name, image } = chatBotList[botType]
  const { setChatbotType } = useChatbotActions()

  return (
    <div className={styles.container} onClick={() => setChatbotType(botType)}>
      <div className={styles.titleBox}>
        <div className={styles.name}>
          <span>{sub}</span>
          <span> {name}</span>
        </div>
      </div>
      <div className={styles.cover} />
      <Image src={image} alt={botType} width={500} height={500} className={styles.image} />
    </div>
  )
}

export default ChatListItem
