import { fetchHashtags } from '../data/hashtag'
import { fetchQuestions } from '../data/question'
import { prismaMock } from '../singleton'
import { res } from './res'

// test('should fetch hashtags', async () => {
//   prismaMock.hashtag.findMany.mockResolvedValue()

//   await expect(fetchHashtags()).resolves.toEqual({})
// })

// test('should fetch questions (initial)', async () => {
//   const searchword = 'test'
//   const tags = ['test']
//   const condition = {
//     where: {
//       AND: [
//         {
//           hashtags: {
//             some: {
//               hashtag: {
//                 title: { in: tags },
//               },
//             },
//           },
//         },
//         {
//           title: {
//             contains: searchword,
//           },
//         },
//       ],
//     },
//     include: {
//       _count: {
//         select: { answers: true },
//       },
//       hashtags: {
//         select: {
//           hashtag: true,
//         },
//       },
//     },
//     take: 5,
//   }

//   prismaMock.question.findMany(condition)

//   await expect(await fetchQuestions(tags, searchword, 0)).resolves.toEqual(res)
// })
