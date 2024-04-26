// 제한 글자 수 넘기면 ... 처리해주는 함수
export const getShortText = (text: string, limit: number) =>
  text.length > limit ? `${text.substring(0, limit)}...` : text
