import { IQuestionDetail } from '@/types/community'
import styles from './DetailContent.module.scss'
import dynamic from 'next/dynamic'
import DetailContentSubtitle from '../DetaiContentSubtitle'

const DetailContentDesc = dynamic(() => import('../DetailContentDesc'), { ssr: false })

function DetailContent({ question }: { question: IQuestionDetail }) {
  return (
    <div className={styles.container}>
      <div className={styles.titleSection}>
        <div className={styles.tagBox}>
          {question.hashtags?.map(each => <span key={each.hashtag.id}>#{each.hashtag.title}</span>)}
        </div>
        <div className={styles.title}>{question.title}</div>
        <DetailContentSubtitle
          id={question.id}
          author={question.memberName}
          authorId={question.memberId}
          createdAt={question.createdAt}
          authId={question.authId}
        />
      </div>
      <DetailContentDesc desc={question.desc} />
    </div>
  )
}

export default DetailContent
