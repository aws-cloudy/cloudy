import { useEffect, useState } from 'react'
import styles from './Activity.module.scss'
import ActivityWrite from './ActivityWrite'
import ActivityComment from './ActivityComment/indext'
import Dropdown from '@/components/common/Dropdown'
import CustomSelect from '@/components/common/CustomSelect'
import { IFilter } from '@/types/learning'
import { IoIosSearch } from 'react-icons/io'
import { getRoadmapComments } from '@/apis/roadmap'

const Activity = ({user}:any) => {
  const [selectedTab, setSelectedTab] = useState('write')  
  const [questionList, setQuestionList] = useState<any[]>([]);
  const [answerList, setAnswerList] = useState<any[]>([]);

  //작성글 더미데이터
  const wData = {
    questionList: [
      {
        id: 1,
        memberId: "0498fd2c-a031-7096-02b6-0dbcbb40c9f0",
        memberName: "김싸피",
        title: "test",
        desc: "I'm trying to read words in a text file. If a word is duplicated I want to increase the Words.frequency +=1 and not add the word to lexicon. It's for an assignment, so nothing specific, just point me in the right direction.\n\n```\nclass Words:\n     def __init__(self, word, frequency, neighbours ):\n         self.word = word \n        self.frequency = frequency \n        self.neighbours = neighbours  \n\ndef read_data(lexicon, filename):\n   file1 = open(filename, 'r')\n   f = file1.readlines()\n   mot = Words\n\n     for line in f:\n       # lexicon.append(line.strip())\n       for word in line.split():\n           word = ''.join([ch for ch in word if ch.isalpha()])\n           mot = word.lower()\n                                 if any(Words.word == word for Words in lexicon):\n                                       #if the word is a duplicate then find the instance of that word in lexicon and frequency += 1\n                                 else:\n                                       lexicon.append(Words(mot.lower(), 0, None))\n```\n\n\nI've tried using list.index(x) to find the lexicon element where said word is, but it keeps getting below error:\n\nAttributeError: type object 'Words' has no attribute 'word'\nI've tried enumerating through it, but that doesn't work either\n\n![alt text](https://ucirshljogphrxuljrju.supabase.co/storage/v1/object/public/cloudy_image/8919a23c-67cd-4ab6-b348-d453915b6c40.jpeg)",
        hit: 0,
        createdAt: "2024-05-03T00:16:52.786Z",
        checkedId: 1,
        _count: {
          answers: 1 
        },
        hashtags: [   
          {
            hashtag: {
              id: 1,
              title: "보안/자격"
            }
          }
        ]
      },
      {
        id: 4,
        memberId: "0498fd2c-a031-7096-02b6-0dbcbb40c9f0",
        memberName: "김싸피",
        title: "test ",
        desc: "test1",
        hit: 0,
        createdAt: "2024-05-03T02:04:36.546Z",
        checkedId: null,
        _count: {
          answers: 0
        },
        hashtags: [
          {
            hashtag: {
              id: 5,
              title: "test"
            }
          },
          {
            hashtag: {
              id: 6,
              title: "db"
            }
          }
        ]
      }
    ],
    count: 2 
  }

  const cData = {
      answersList: [
        {
          id: 1,
          memberId: "0498fd2c-a031-7096-02b6-0dbcbb40c9f0",
          memberName: "김싸피",
          createdAt: "2024-05-03T00:17:35.937Z",
          desc: "Words is a generic type. You can't ask for an attribute from a generic type because the initialization function is the function that defines this attribute. You need to initialize it first.\nmy_words = [Words(\"the\", 0, [\"is\"]), ...]  print(my_words[0].word == \"the\") # prints true# gets the *initialized* word attribute\nIt also might help not to name it Words and instead something like Word because the name Words implies that the object contains multiple words.\nLastly in this line:\nif any(Words.word == word for Words in lexicon):\nYou write Words.word for Words in lexicon accessing the nonexistent word attribute from the non-initialized class Words. Try a different variable name like [my_word.word for my_word in lexicon]",
          questionId: 1
        },
        {
          id: 5,
          memberId: "0498fd2c-a031-7096-02b6-0dbcbb40c9f0",
          memberName: "김싸피",
          createdAt: "2024-05-04T03:04:16.947Z",
          desc: "test ",
          questionId: 4
        }
      ],
      count: 2 
    }
  

  const drop = [
    { value: '', name: '전체', category: 'question' },
    { value: '', name: '작성일순', category: 'question' },
  ]

  const [options, setOptions] = useState<IFilter>(drop[0])

  const fetchQuestions = async () => {
    try {
      const res = await fetch('/api/mypage/questions');      
      const data = await res.json();
      setQuestionList(data.questionList);
      
    } catch (error) {
      console.log('질문 가져오기 실패', error)
    }
  };

  const fetchAnswers = async () => {
    try {
      const res = await fetch('/api/mypage/answers');      
      const data = await res.json();
      setAnswerList(data.answerList);
    } catch (error) {
      console.log('답변 가져오기 실패', error)
    }
  };

  const sortByDateFn = (data: any[]) => {
    return [...data].sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime();
      const dateB = new Date(b.createdAt).getTime();
      console.log('A,b', dateA, dateB)
      return dateB - dateA; // 오름차순으로 정렬하려면 dateA - dateB
    });
  };

  useEffect(() => {
    // fetchQuestions();
    // fetchAnswers();
    
    setQuestionList(wData.questionList)
    setAnswerList(cData.answersList)
  }, [])

  useEffect(() => {
    if (selectedTab === 'write' && options.name === '작성일순') {
      setQuestionList(sortByDateFn(wData.questionList));
    } else if (selectedTab === 'comment' && options.name === '작성일순') {
      setAnswerList(sortByDateFn(cData.answersList));
    } else if (options.name === '전체') {
      setQuestionList(wData.questionList);
      setAnswerList(cData.answersList);
    }
    
  }, [options, selectedTab]);

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
          <input type="text" placeholder="검색어를 입력해주세요." className={styles.input} />
        </div>
      </div>
      {selectedTab === 'write' && <ActivityWrite posts={questionList} />}
      {selectedTab === 'comment' && <ActivityComment comments={answerList} />}
    </section>
  )
}

export default Activity
