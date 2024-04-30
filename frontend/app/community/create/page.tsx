'use client'
import axios from 'axios'
import React, { useEffect, useRef, useState } from 'react'
import { useForm } from 'react-hook-form'

import '@toast-ui/editor/dist/toastui-editor.css'

import styles from './page.module.scss'
import EditorHashtag from '@/components/community/create/EditorHashtag'
import EditorFooter from '@/components/community/create/EditorFooter'

import { IHashtag, IImage } from '@/types/community'
import { commuURL } from '@/apis/urls'
import dynamic from 'next/dynamic'
import { Editor as EditorType } from '@toast-ui/react-editor'
import Layout from '@/components/common/Layout'
import { useRouter } from 'next/navigation'
import EditorTitle from '@/components/community/create/EditorTitle'

const EditorBody = dynamic(() => import('@/components/community/create/EditorBody'), { ssr: false })

const CommunityCreatepage = () => {
  const [tags, setTags] = useState<IHashtag[]>([])
  const [images, setImages] = useState<IImage[]>([])
  const { register, getValues } = useForm<{ title: string }>()
  const editorRef = useRef<EditorType>(null)
  const router = useRouter()

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const editor = editorRef.current
    if (!editor) return
    const title = getValues('title')
    let desc = editor.getInstance().getMarkdown()

    images.forEach(e => {
      const { localUrl, dbUrl } = e
      desc = desc.replace(localUrl, dbUrl)
    })

    const res = await axios.post(`${commuURL}/question/create`, { tags, title, desc })
    router.push(`/community/detail/${res.data.question.id}`)
  }

  useEffect(() => {
    console.log(images)
  }, [images])

  return (
    <Layout>
      <form className={styles.form} onSubmit={onSubmit}>
        <EditorHashtag tags={tags} setTags={setTags} />
        <EditorTitle register={register} getValues={getValues} />
        <EditorBody editorRef={editorRef} setImages={setImages} />
        <EditorFooter />
      </form>
    </Layout>
  )
}

export default CommunityCreatepage
