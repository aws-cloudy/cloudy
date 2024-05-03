import { commuURL } from '@/apis/urls'
import CreateForm from '@/components/community/CreateForm'
import { IQuestion } from '@/types/community'
import { getUser } from '@/utils/getUser'
import axios from 'axios'
import { unstable_noStore as noStore } from 'next/cache'
import styles from './page.module.scss'

const CommunityUpdatepage = async ({ params }: { params: { id: string } }) => {
  noStore()
  const user = await getUser()
  const post = await axios.get(`${commuURL}/question/detail`, {
    params: { id: params.id },
  })
  const isWriter = user?.id === post.data.memberId

  if (!post.data.id) {
    return <div className={styles.notfound}>삭제되었거나 존재하지 않는 게시글입니다.</div>
  }

  if (!isWriter) {
    return <div>권한이 없습니다.</div>
  }

  const data = post.data as IQuestion
  const tags = data.hashtags.map(each => each.hashtag)

  return <CreateForm id={data.id} authorId={data.memberId} title={data.title} desc={data.desc} hashtags={tags} />
}
export default CommunityUpdatepage
