import prisma from '../client'

export async function fetchHashtags() {
  try {
    const data = await prisma.hashtag.findMany({
      orderBy: [{ title: 'asc' }],
    })

    return data
  } catch (e) {
    console.log('An error occured during fetching hashtags...')
  }
}
