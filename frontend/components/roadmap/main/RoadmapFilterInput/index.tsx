import React from 'react'
import CustomInput from '@/components/common/CustomInput'
import { IRoadmapFilterInput } from '@/types/roadmap'
import { useRoadmapActions } from '@/stores/roadmap'

const RoadmapFilterInput = (props: IRoadmapFilterInput) => {
  const { value, setValue, job, service } = props

  const { setRoadmapSearchValue } = useRoadmapActions()

  // 검색 버튼 클릭시
  const onClick = () => {
    setRoadmapSearchValue({ job: job.value, service: service.value, keyword: value })
  }

  return <CustomInput value={value} setValue={setValue} onClick={onClick} />
}

export default RoadmapFilterInput
