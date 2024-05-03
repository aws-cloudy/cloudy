import axiosInstance from '@/utils/axiosInstance'

export const fetchLearningRecom = async (job: number) => {
  const res = await axiosInstance.get(`/v1/my/learnings/search/job/${job}`, { params: { count: 10 } })
  return res.data.learningList
}
