import CreateForm from '@/components/community/CreateForm'
import { getUser } from '@/utils/getUser'

const CommunityCreatepage = async () => {
  const user = await getUser()

  if (!user) {
    return <div>권한이 없습니다.</div>
  }

  return <CreateForm />
}

export default CommunityCreatepage
