import { useForm } from 'react-hook-form'
import styles from './ChatRoomInput.module.scss'
import { IChatRoomInput } from '@/types/chatbot'

function ChatRoomInput({ setMessages }: IChatRoomInput) {
  const { register, handleSubmit, getValues, reset } = useForm<{ msg: string }>()

  const onSubmit = () => {
    const content = getValues('msg')
    if (content === '') return
    setMessages(prev => [...prev, { sender: 'user', content }])
    reset()
  }

  return (
    <form className={styles.container} onSubmit={handleSubmit(onSubmit)} name="messageForm" data-testid="form">
      <input
        type="text"
        className={styles.input}
        placeholder="메세지 입력..."
        defaultValue={''}
        {...register('msg')}
        required
        data-testid="input"
      />
    </form>
  )
}

export default ChatRoomInput
