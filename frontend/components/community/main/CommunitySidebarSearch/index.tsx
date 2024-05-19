import styles from './CommunitySidebarSearch.module.scss'
import { BiSearch } from 'react-icons/bi'
import { useForm } from 'react-hook-form'
import { useCommuSearchActions, useCommuSearchKeyword } from '@/stores/communityStore'

function CommunitySidebarSearch() {
  const { register, handleSubmit, getValues } = useForm<{ keyword: string }>()
  const searchWord = useCommuSearchKeyword()
  const { setKeyword } = useCommuSearchActions()

  const onSubmit = () => {
    const keyword = getValues('keyword')
    setKeyword(keyword)
  }

  return (
    <form className={styles.container} id="communitySearchForm" data-testid="form" onSubmit={handleSubmit(onSubmit)}>
      <BiSearch className={styles.icon} />
      <input
        type="text"
        defaultValue={searchWord}
        placeholder="검색어를 입력하세요..."
        className={styles.input}
        {...register('keyword')}
      />
    </form>
  )
}

export default CommunitySidebarSearch
