import Layout from '@/components/common/Layout'
import DetailAnswer from '@/components/community/detail/DetailAnswer'
import DetailContent from '@/components/community/detail/DetailContent'
import '@toast-ui/editor/dist/toastui-editor.css'

import { commuURL } from '@/apis/urls'
import { IQuestion, IQuestionAnswer, IQuestionDetail } from '@/types/community'
import { ICommunityDetailPage } from '@/types/communityProps'
import axios from 'axios'
import styles from './page.module.scss'
import { getUser } from '@/utils/getUser'

const getQuestion = async (id: string) => {
  const res = await axios.get(`${commuURL}/question/detail`, { params: { id } })
  return res.data as IQuestion
}

async function CommunityDetailPage({ params: { id } }: ICommunityDetailPage) {
  const question = await getQuestion(id)
  if (!question.id) return <div>삭제되었거나 존재하지 않는 게시글입니다.</div>
  const { id: questionId, memberId, title, hashtags, desc, createdAt, memberName, answers, checkedId, hit } = question
  const user = await getUser()
  const Q: IQuestionDetail = {
    id: questionId,
    memberId,
    title,
    hashtags,
    desc,
    createdAt,
    memberName,
    hit,
    authId: user?.id,
  }
  const A: IQuestionAnswer = { id: questionId, answers, checkedId, authId: user?.id, isWriter: user?.id === memberId }

  return (
    <Layout>
      <div className={styles.inner}>
        <DetailContent question={Q} />
        <DetailAnswer answer={A} />
      </div>
    </Layout>
  )
}

export default CommunityDetailPage
