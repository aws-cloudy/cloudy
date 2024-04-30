'use client'

import { IEditorBody } from '@/types/communityProps'
import { Editor } from '@toast-ui/react-editor'
import React from 'react'
import { createClient } from '@supabase/supabase-js'

const url = process.env.NEXT_PUBLIC_SUPABASE_URL!
const key = process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY!
const supabase = createClient(url, key)

type HookCallback = (url: string, text?: string) => void

const EditorBody = ({ editorRef, setImages }: IEditorBody) => {
  const handleImage = async (blob: Blob | File, callback: HookCallback) => {
    const localUrl = await URL.createObjectURL(blob)
    callback(localUrl, 'alt text')

    const uuid = crypto.randomUUID()
    const { data } = await supabase.storage.from('cloudy_image').upload(`/${uuid}.jpeg`, blob)

    if (data) {
      const { path } = data
      const urlSource = await supabase.storage.from('cloudy_image').getPublicUrl(path)
      const dbUrl = urlSource.data.publicUrl
      if (dbUrl) {
        setImages(prev => [...prev, { localUrl, dbUrl }])
      }
    }

    return false
  }

  return (
    <Editor
      ref={editorRef}
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
