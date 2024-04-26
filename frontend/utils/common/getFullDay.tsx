export const getFullDay = (date: Date) => {
  const year = date.getFullYear()
  const month = (date.getUTCMonth() + 1).toString().padStart(2, '0')
  const day = date.getUTCDay().toString().padStart(2, '0')
  const time = date.getUTCHours().toString().padStart(2, '0')
  const min = date.getUTCMinutes().toString().padStart(2, '0')
  return `${year}-${month}-${day} ${time}:${min}`
}
