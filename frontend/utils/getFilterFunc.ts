import { IFilter } from '@/types/learning'

// IFilter 배열을 ,를 붙이면서 하나의 텍스트로 반환하는 함수
export const getTextFilter = (data: IFilter[]): string => {
  return data.map(v => v.value).join(',')
}

// query string을 IFilter 배열로 반환
export const extractArrFromQuery = (queryString: string | null, data: IFilter[]) => {
  const list: IFilter[] = []
  const addedSet = new Set() // 이미 추가된 값 제거하기 위해 사용

  if (queryString && queryString.length !== 0) {
    const values = queryString.split(',')
    for (const value of values) {
      if (!addedSet.has(value)) {
        const isValue = data.find(v => v.value === value)
        if (isValue) {
          list.push(isValue)
          addedSet.add(value)
        }
      }
    }
  }
  return list
}
