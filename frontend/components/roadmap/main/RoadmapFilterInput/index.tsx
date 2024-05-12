import React from 'react'
import CustomInput from '@/components/common/CustomInput'
import { IRoadmapFilterInput } from '@/types/roadmap'

const RoadmapFilterInput = (props: IRoadmapFilterInput) => {
  const { value, setValue, onSearch } = props

  return <CustomInput value={value} setValue={setValue} onSearch={onSearch} />
}

export default RoadmapFilterInput
