'use client'

import { IDetailContentDesc } from '@/types/communityProps'
import { Viewer } from '@toast-ui/react-editor'
import styles from './DetailContentDesc.module.scss'

function DetailContentDesc({ desc }: IDetailContentDesc) {
  return (
    <div className={styles.container}>
      <Viewer initialValue={desc} />
    </div>
  )
}

export default DetailContentDesc
