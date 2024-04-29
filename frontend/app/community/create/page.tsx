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
import dynamic from 'next/dynamic'
import { Editor as EditorType } from '@toast-ui/react-editor'
import Layout from '@/components/common/Layout'

const EditorBody = dynamic(() => import('@/components/community/create/EditorBody'), { ssr: false })

const CommunityCreatepage = () => {
  const [tags, setTags] = useState<IHashtag[]>([])
  const { register, getValues } = useForm<{ title: string }>()
  const editorRef = useRef<EditorType>(null)

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
    <Layout>
      <form className={styles.form} onSubmit={onSubmit}>
        <EditorHashtag tags={tags} setTags={setTags} />
        <input type="text" className={styles.title} placeholder="제목을 입력하세요" {...register('title')} />
        <EditorBody editorRef={editorRef} />
        <EditorFooter />
      </form>
    </Layout>
  )
}

export default CommunityCreatepage
