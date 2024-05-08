import server from '@/utils/axiosServer'

export const fetchLearningRecomWithoutJob = async () => {
  const response = await server
    .get(`/learnings/search/job`)
    .then(res => res.data.learningList)
    .catch(err => err)
  return response
}
