'use client'

import { Editor, EditorProps } from '@toast-ui/react-editor'
import React, { forwardRef } from 'react'

const EditorBody = ({ editorRef }: { editorRef: React.RefObject<Editor> }) => {
  return <Editor ref={editorRef} initialEditType="wysiwyg" hideModeSwitch={true} />
}

export default EditorBody
