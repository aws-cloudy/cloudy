import styles from './ChatRoomMessage.module.scss'
import { IMessage } from '@/types/chatbot'
import { useChatbotType } from '@/stores/chatbotStore'
import { chatBotList } from '@/constants/chatbot'
import Image from 'next/image'
import { useMemo } from 'react'

function ChatRoomMessage({ isUserSent, content, waiting }: IMessage) {
  const botType = useChatbotType()
  const icon = chatBotList[botType].image
  const regex = new RegExp(/(\()(?=(?=https:\/\/)|(?=http:\/\/))(.*)(\))/g)
  const convertedText = useMemo(
    () => content.replace(regex, `$1<a href="$2">$2</a>$3`).replace(/(?:\n)/g, '</br>'),
    [content],
  )

  return (
    <div className={styles.container}>
      <div className={`${styles.inner} ${!isUserSent ? styles.bot : styles.user}`}>
        {!isUserSent && <Image src={icon} alt={botType} width={500} height={500} className={styles.botIcon} />}
        <div className={`${styles.message} ${!isUserSent ? styles.messageBot : styles.messageUser}`}>
          {waiting ? (
            <Image src="/gif/spinner_transparent.gif" alt="loading" width={30} height={30} />
          ) : (
            <span dangerouslySetInnerHTML={{ __html: convertedText }}></span>
          )}
        </div>
      </div>
    </div>
  )
}

export default ChatRoomMessage
