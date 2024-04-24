'use client'

import { Editor, Viewer } from '@toast-ui/react-editor'
import '@toast-ui/editor/dist/toastui-editor.css' // Editor's Style
import { useRef } from 'react'

const CommunityCreatepage = () => {
  const editorRef = useRef<Editor>(null)
  const viewerRef = useRef<Viewer>(null)

  const handleSubmit = () => {
    const editor = editorRef.current
    const viewer = viewerRef.current
    if (!editor) return
    if (!viewer) return
    console.log(editor.getInstance().getMarkdown())
    viewer.getInstance().setMarkdown(editor.getInstance().getMarkdown())
  }

  return (
    <>
      <Editor ref={editorRef} initialEditType="wysiwyg" hideModeSwitch={true} />
      <button onClick={() => handleSubmit()}>ㅇㅋ</button>
      <Viewer ref={viewerRef} />
    </>
  )
}

export default CommunityCreatepage
