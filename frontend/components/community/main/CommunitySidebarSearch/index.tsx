import styles from './CommunitySidebarSearch.module.scss'
import { BiSearch } from 'react-icons/bi'
import { useForm } from 'react-hook-form'

function CommunitySidebarSearch() {
  const { register, handleSubmit, getValues } = useForm<{ keyword: string }>()
  const onSubmit = () => {
    const keyword = getValues('keyword')
    console.log(keyword)
  }

  return (
    <form className={styles.container} id="communitySearchForm" data-testid="form" onSubmit={handleSubmit(onSubmit)}>
      <BiSearch className={styles.icon} />
      <input
        type="text"
        defaultValue={''}
        placeholder="검색어를 입력하세요..."
        className={styles.input}
        {...register('keyword', { required: true })}
      />
    </form>
  )
}

export default CommunitySidebarSearch
