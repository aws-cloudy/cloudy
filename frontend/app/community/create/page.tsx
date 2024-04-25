import '@toast-ui/editor/dist/toastui-editor.css'
import styles from './page.module.scss'
import EditorHashtag from '@/components/community/create/EditorHashtag'
import EditorFooter from '@/components/community/create/EditorFooter'
import dynamic from 'next/dynamic'

const CommunityCreatepage = () => {
  const EditorComponent = dynamic(() => import('@/components/community/create/EditorComponent'), {
    ssr: false,
  })

  return (
    <div className={styles.container}>
      <form className={styles.form}>
        <EditorHashtag />
        <input type="text" className={styles.title} placeholder="제목을 입력하세요" />
        <EditorComponent />
        <EditorFooter />
      </form>
    </div>
  )
}

export default CommunityCreatepage
