'use client'

import { supabase } from '@/apis/supabase'
import { IEditorBody } from '@/types/communityProps'
import { Editor } from '@toast-ui/react-editor'
import React from 'react'

type HookCallback = (url: string, text?: string) => void

const EditorBody = ({ editorRef, setImages, orig }: IEditorBody) => {
  const defaultValue = orig ? orig : ''

  const handleImage = async (blob: Blob | File, callback: HookCallback) => {
    const localUrl = await URL.createObjectURL(blob)
    callback(localUrl, 'alt text')
    setImages(prev => [...prev, { localUrl, file: blob }])

    return false
  }

  return (
    <Editor
      ref={editorRef}
      initialValue={orig}
      initialEditType="wysiwyg"
      hideModeSwitch={true}
      height="500px"
      hooks={{
        addImageBlobHook: handleImage,
      }}
    />
  )
}

export default EditorBody
