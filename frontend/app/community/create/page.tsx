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
import { supabase } from '@/apis/supabase'

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
    const newImg = images.filter(each => desc.match(each.localUrl))
    const imageData: { path: string; url: string }[] = []

    const uploadImages = newImg.map(async each => {
      const uuid = crypto.randomUUID()
      const { data } = await supabase.storage.from('cloudy_image').upload(`/${uuid}.jpeg`, each.file)

      if (data) {
        const { path } = data
        const urlSource = await supabase.storage.from('cloudy_image').getPublicUrl(path)
        const dbUrl = urlSource.data.publicUrl
        imageData.push({ path, url: dbUrl })
        desc = desc.replace(each.localUrl, dbUrl)
      }
    })

    await Promise.all(uploadImages)

    const res = await axios.post(`${commuURL}/question/create`, {
      tags,
      title,
      desc,
      imageData,
    })
    router.push(`/community/detail/${res.data.question.id}`)
  }

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
