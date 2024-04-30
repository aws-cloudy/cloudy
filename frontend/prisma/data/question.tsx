import prisma from '../client'

export async function fetchQuestionDetail(id: number) {
  try {
    const data = await prisma.question.findUnique({
      where: { id },
      include: {
        answers: true,
        hashtags: {
          select: {
            hashtag: true,
          },
        },
      },
    })
    if (data) {
      return data
    }
  } catch (e) {
    console.log('An error occured during fetching question detail...')
  }
}
