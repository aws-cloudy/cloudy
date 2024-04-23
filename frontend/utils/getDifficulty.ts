import styles from '@/components/common/LearningItem/LearningItem.module.scss'

// 영어로된 난이도를 {텍스트, 클래스}로 바꿔서 반환
export const getDifficulty = (difficulty: string) => {
  switch (difficulty) {
    case 'Fundamental':
      return { text: '기초', class: styles.fundamental }
    case 'Intermediate':
      return { text: '중급', class: styles.intermediate }
    case 'Advanced':
      return { text: '고급', class: styles.advanced }
    default:
      return { text: '기초', class: styles.fundamental }
  }
}
