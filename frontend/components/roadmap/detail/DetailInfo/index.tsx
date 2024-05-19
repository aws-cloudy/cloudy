import React from 'react'
import styles from './DetailInfo.module.scss'
import ReactMarkdown from 'react-markdown'
import remarkGfm from 'remark-gfm'
import rehypeHighlight from 'rehype-highlight'
import 'highlight.js/styles/atom-one-dark.css'
import { IRoadmapCardExtend } from '@/types/roadmap'
import Image from 'next/image'

const DetailInfo = ({ data }: { data: IRoadmapCardExtend }) => {
  return (
    <>
      <div className={styles.imgWrap}>
        <Image src={data.thumbnail} alt={data.title} priority fill sizes="auto" />
      </div>
      <div className={styles.container}>
        <ReactMarkdown children={data.desc} remarkPlugins={[remarkGfm]} rehypePlugins={[rehypeHighlight]} />
      </div>
    </>
  )
}

export default DetailInfo
