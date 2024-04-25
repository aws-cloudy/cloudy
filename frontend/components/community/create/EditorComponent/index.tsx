'use client'

import { useRef } from 'react'
import { Editor } from '@toast-ui/react-editor'
import styles from './EditorComponent.module.scss'

function EditorComponent() {
  const editorRef = useRef<Editor>(null)

  const handleSubmit = () => {
    const editor = editorRef.current
    if (!editor) return
    console.log(editor.getInstance().getMarkdown())
  }

  return <Editor ref={editorRef} initialEditType="wysiwyg" hideModeSwitch={true} className={styles.editor} />
}

export default EditorComponent
