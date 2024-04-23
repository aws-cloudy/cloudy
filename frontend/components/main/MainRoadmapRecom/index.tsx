import MainRoadmapRecomItem from '../MainRoadmapRecomItem'
import styles from './MainRoadmapRecom.module.scss'

import { FaChevronLeft } from 'react-icons/fa6'
import { FaChevronRight } from 'react-icons/fa6'

const dummy = [
  {
    title: '더미 데이터',
    content:
      '글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 ',
    link: 'http://example.com/',
  },
  {
    title:
      '더미 데이터 더미 데이터 더미 데이터 더미 데이터 더미 데이터 더미 데이터 더미 데이터 더미 데이터 더미 데이터 더미 데이터더미 데이터 더미 데이터 더미 데이터 더미 데이터',
    content:
      '글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 ',
    link: 'http://example.com/',
  },
  {
    title: '더미 데이터 더미 데이터 더미 데이터 더미 데이터 더미 데이터',
    content:
      '글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기 글자수 세기',
    link: 'http://example.com/',
  },
]

function MainRoadmapRecom() {
  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <div className={styles.titleBox}>
          <p className={styles.title}>
            AWS <span>100%</span> 활용하기
          </p>
          <div className={styles.border} />
          <div className={styles.buttonBox}>
            <FaChevronLeft className={styles.button} />
            <FaChevronRight className={styles.button} />
          </div>
        </div>
        <div className={styles.roadmapBox}>
          {dummy.map((e, i) => (
            <MainRoadmapRecomItem key={i} title={e.title} content={e.content} link={e.link} />
          ))}
        </div>
      </div>
    </div>
  )
}

export default MainRoadmapRecom
