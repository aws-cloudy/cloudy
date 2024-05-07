import React from 'react'
import styles from './DetailInfo.module.scss'
import ReactMarkdown from 'react-markdown'
import remarkGfm from 'remark-gfm'
import rehypeHighlight from 'rehype-highlight'
import 'highlight.js/styles/atom-one-dark.css'

const DetailInfo = ({ data }: { data: string }) => {
  return (
    <div className={styles.container}>
      <ReactMarkdown children={data} remarkPlugins={[remarkGfm]} rehypePlugins={[rehypeHighlight]} />
    </div>
  )
}

export default DetailInfo
