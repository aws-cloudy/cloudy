import Button from '@/components/common/Button'
import styles from './EditorFooter.module.scss'

function EditorFooter() {
  return (
    <div className={styles.container}>
      <button className={styles.show}>미리보기</button>
      <Button type="submit" width="100px">
        저장
      </Button>
    </div>
  )
}

export default EditorFooter
