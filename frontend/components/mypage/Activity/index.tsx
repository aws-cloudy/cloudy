import { useEffect, useState } from 'react'
import styles from './Activity.module.scss'
import ActivityWrite from './ActivityWrite'
import ActivityComment from './ActivityComment/indext'
import Dropdown from '@/components/common/Dropdown'
import CustomSelect from '@/components/common/CustomSelect'
import { IFilter } from '@/types/learning'
import { IoIosSearch } from 'react-icons/io'
import { getRoadmapComments } from '@/apis/roadmap'

const Activity = ({ user }: any) => {
  const [selectedTab, setSelectedTab] = useState('write')
  const [questionList, setQuestionList] = useState<any[]>([])
  const [answerList, setAnswerList] = useState<any[]>([])
  const [searchQuery, setSearchQuery] = useState<string>('')
  const [originQuestionList, setOriginQuestionList] = useState<any[]>([])
  const [originAnswerList, setOriginAnswerList] = useState<any[]>([])

  const drop = [
    { value: '', name: '기본순', category: 'question' },
    { value: '', name: '최신순', category: 'question' },
  ]

  const [options, setOptions] = useState<IFilter>(drop[0])

  const fetchQuestions = async () => {
    try {
      const res = await fetch('/api/mypage/questions')
      const data = await res.json()
      setQuestionList(data.questionList)
      setOriginQuestionList(data.questionList)
      console.log(questionList)
    } catch (error) {
      console.log('질문 가져오기 실패', error)
    }
  }

  const fetchAnswers = async () => {
    try {
      const res = await fetch('/api/mypage/answers')
      const data = await res.json()
      setAnswerList(data.answersList)
      setOriginAnswerList(data.answersList)
      console.log(answerList)
    } catch (error) {
      console.log('답변 가져오기 실패', error)
    }
  }

  const sortByDateFn = (data: any[]) => {
    return [...data].sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime()
      const dateB = new Date(b.createdAt).getTime()
      console.log('A,b', dateA, dateB)
      return dateB - dateA // 오름차순으로 정렬하려면 dateA - dateB
    })
  }

  const filterPosts = (posts: any[], query: string, type: string) => {
    return posts.filter(post => {
      if (type === 'write') {
        return post.title && post.title.toLowerCase().includes(query.toLowerCase())
      } else if (type === 'comment') {
        return post.desc && post.desc.toLowerCase().includes(query.toLowerCase())
      }
      return false
    })
  }

  // 검색어 입력 핸들러
  const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(e.target.value)
  }

  useEffect(() => {
    fetchQuestions()
    fetchAnswers()
  }, [])

  useEffect(() => {
    if (selectedTab === 'write' && options.name === '최신순') {
      setQuestionList(sortByDateFn(originQuestionList))
    } else if (selectedTab === 'comment' && options.name === '최신순') {
      setAnswerList(sortByDateFn(originAnswerList))
    } else if (options.name === '기본순') {
      setQuestionList(originQuestionList)
      setAnswerList(originAnswerList)
    }
  }, [options, selectedTab])

  return (
    <section className={styles.section}>
      <div className={styles.intro}>활동내역</div>
      <div className={styles.tab}>
        <div
          className={selectedTab === 'write' ? styles.activeTab : styles.inActiveTab}
          onClick={() => {
            setSelectedTab('write')
          }}
        >
          작성한 글
        </div>
        <div
          className={selectedTab === 'comment' ? styles.activeTab : styles.inActiveTab}
          onClick={() => {
            setSelectedTab('comment')
          }}
        >
          작성한 댓글
        </div>
      </div>
      <div className={styles.row}>
        <CustomSelect item={options} setItem={setOptions} options={drop} />
        <div className={styles.inputContainer}>
          <IoIosSearch className={styles.icon} />
          <input
            type="text"
            placeholder="검색어를 입력해주세요."
            value={searchQuery}
            onChange={handleSearch}
            className={styles.input}
          />
        </div>
      </div>
      {selectedTab === 'write' && <ActivityWrite posts={filterPosts(questionList, searchQuery, 'write')} />}
      {selectedTab === 'comment' && <ActivityComment comments={filterPosts(answerList, searchQuery, 'comment')} />}
    </section>
  )
}

export default Activity
