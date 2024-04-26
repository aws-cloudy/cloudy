import React from 'react'
import CustomInput from '@/components/common/CustomInput'
import { IRoadmapFilterInput } from '@/types/roadmap'

const RoadmapFilterInput = (props: IRoadmapFilterInput) => {
  const { value, setValue } = props

  return (
    <div>
      <CustomInput value={value} setValue={setValue} />
    </div>
  )
}

export default RoadmapFilterInput
