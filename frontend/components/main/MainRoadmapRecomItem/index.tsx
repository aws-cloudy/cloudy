import Image from 'next/image'
import styles from './MainRoadmapRecomItem.module.scss'

type Props = {
  title: string
  content: string
  id: number
  imgSrc: string
}

function MainRoadmapRecomItem({ title, content, id, imgSrc }: Props) {
  const shortTitle = title.replace(/(?<=^.{60}).+/, '...')
  const shortContent = content.replace(/(?<=^.{200}).+/, '...')

  return (
    <div className={styles.container}>
      <div className={styles.imgWrap}>
        <Image src={imgSrc} alt={title} fill sizes="auto" />
      </div>
      <div className={styles.textBox}>
        <p className={styles.title}>{shortTitle}</p>
        <p className={styles.content}>{shortContent}</p>
        <a href={`/roadmap/${id}`} className={styles.link}>
          {'자세히 보기 〉'}
        </a>
      </div>
    </div>
  )
}

export default MainRoadmapRecomItem
