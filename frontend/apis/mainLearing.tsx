import axiosInstance from '@/utils/axiosInstance'

export const fetchLearningRecomWithoutJob = async () => {
  const res = await axiosInstance.get(`/learnings/search/job`)
  return res.data.learningList
}
