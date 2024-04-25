'use client'
import axios from 'axios'
import React, { useRef, useState } from 'react'
import { useForm } from 'react-hook-form'

import '@toast-ui/editor/dist/toastui-editor.css'

import styles from './page.module.scss'
import EditorHashtag from '@/components/community/create/EditorHashtag'
import EditorFooter from '@/components/community/create/EditorFooter'

import { IHashtag } from '@/types/community'
import { commuURL } from '@/apis/urls'
import { Editor } from '@toast-ui/react-editor'

const CommunityCreatepage = () => {
  const [tags, setTags] = useState<IHashtag[]>([])
  const editorRef = useRef<Editor>(null)
  const { register, getValues } = useForm<{ title: string }>()

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const editor = editorRef.current
    if (!editor) return

    const title = getValues('title')
    const desc = editor.getInstance().getMarkdown()
    const res = await axios.post(`${commuURL}/question/create`, { tags, title, desc })
    console.log(res)
  }

  return (
    <div className={styles.container}>
      <form className={styles.form} onSubmit={onSubmit}>
        <EditorHashtag tags={tags} setTags={setTags} />
        <input type="text" className={styles.title} placeholder="제목을 입력하세요" {...register('title')} />
        <Editor ref={editorRef} initialEditType="wysiwyg" hideModeSwitch={true} className={styles.editor} />
        <EditorFooter />
      </form>
    </div>
  )
}

export default CommunityCreatepage
