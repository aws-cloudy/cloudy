import styles from './MainRoadmapRecomItem.module.scss'

type Props = {
  title: string
  content: string
  id: number
}

function MainRoadmapRecomItem({ title, content, id }: Props) {
  const shortTitle = title.length > 60 ? title.substring(0, 60) + '...' : title
  const shortContent = content.length > 200 ? content.substring(0, 200) + '...' : content

  return (
    <div className={styles.container}>
      <p className={styles.title}>{shortTitle}</p>
      <p className={styles.content}>{shortContent}</p>
      <a href={`/roadmap/${id}`} className={styles.link}>
        {'자세히 보기 〉'}
      </a>
    </div>
  )
}

export default MainRoadmapRecomItem
