'use client'

import axios from 'axios'
import React, { useRef, useState } from 'react'
import { useForm } from 'react-hook-form'
import { commuURL } from '@/apis/urls'
import { useRouter } from 'next/navigation'
import dynamic from 'next/dynamic'
import { supabase } from '@/apis/supabase'

import '@toast-ui/editor/dist/toastui-editor.css'

import styles from './CreateForm.module.scss'
import EditorHashtag from '@/components/community/create/EditorHashtag'
import EditorFooter from '@/components/community/create/EditorFooter'
import EditorTitle from '@/components/community/create/EditorTitle'

import Layout from '@/components/common/Layout'
import { IHashtag, IImage, IUpdateQuestion } from '@/types/community'
import { Editor as EditorType } from '@toast-ui/react-editor'
import { Hashtag } from '@prisma/client'

const EditorBody = dynamic(() => import('@/components/community/create/EditorBody'), { ssr: false })

function CreateForm({ id, authorId, desc, hashtags, title }: IUpdateQuestion) {
  const [tags, setTags] = useState<IHashtag[]>(hashtags ? hashtags : [])
  const [images, setImages] = useState<IImage[]>([])
  const { register, getValues } = useForm<{ title: string }>()
  const editorRef = useRef<EditorType>(null)
  const router = useRouter()
  const isUpdate = Boolean(id)

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

    if (id && isUpdate) {
      const res = await axios.put(`${commuURL}/question/update`, {
        questionId: id,
        tags,
        title,
        desc,
        imageData,
      })
      router.prefetch(`/community/detail/${id}`)
      router.push(`/community/detail/${id}`)
    } else {
      const res = await axios.post(`${commuURL}/question/create`, {
        tags,
        title,
        desc,
        imageData,
      })
      router.push(`/community/detail/${res.data.question.id}`)
    }
  }

  return (
    <Layout>
      <form className={styles.form} onSubmit={onSubmit}>
        <EditorHashtag tags={tags} setTags={setTags} />
        <EditorTitle register={register} orig={title} />
        <EditorBody editorRef={editorRef} setImages={setImages} orig={desc} />
        <EditorFooter />
      </form>
    </Layout>
  )
}

export default CreateForm
