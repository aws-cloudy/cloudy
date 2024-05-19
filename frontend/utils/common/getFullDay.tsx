export const getFullDay = (date: Date) => {
  console.log(date)
  const year = date.getFullYear()
  const month = (date.getMonth() + 1).toString().padStart(2, '0')
  const day = date.getDate().toString().padStart(2, '0')
  const time = date.getHours().toString().padStart(2, '0')
  const min = date.getMinutes().toString().padStart(2, '0')
  return `${year}-${month}-${day} ${time}:${min}`
}

export const getDate = (date: Date) => {
  date.setHours(date.getHours() + 9)
  const year = date.getFullYear()
  const month = (date.getMonth() + 1).toString().padStart(2, '0')
  const day = date.getDay().toString().padStart(2, '0')

  return `${year}-${month}-${day}`
}

export const getTime = (date: Date) => {
  date.setHours(date.getHours() + 9)

  const time = date.getHours().toString().padStart(2, '0')
  const min = date.getMinutes().toString().padStart(2, '0')
  return `${time}:${min}`
}
