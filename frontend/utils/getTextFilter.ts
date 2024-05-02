import { IFilter } from '@/types/learning'

// 필터 배열을 ,를 붙이면서 하나의 텍스트로 반환하는 함수
export const getTextFilter = (data: IFilter[]): string => {
  return data.map(v => v.value).join(',')
}
