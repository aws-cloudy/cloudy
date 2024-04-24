'use client'

import { Editor } from '@toast-ui/react-editor'
import { useRef } from 'react'
import styles from './EditorComponent.module.scss'
import EditorHashtag from '../EditorHashtag'

function EditorComponent() {
  const editorRef = useRef<Editor>(null)

  const handleSubmit = () => {
    const editor = editorRef.current
    if (!editor) return
    console.log(editor.getInstance().getMarkdown())
  }

  return (
    <form className={styles.container}>
      <EditorHashtag />
      <input type="text" className={styles.title} placeholder="제목을 입력하세요" />
      <Editor ref={editorRef} initialEditType="wysiwyg" hideModeSwitch={true} className={styles.editor} />
      <button onClick={() => handleSubmit()}>ㅇㅋ</button>
    </form>
  )
}

export default EditorComponent
