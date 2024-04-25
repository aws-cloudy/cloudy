import React from 'react'
import { IButton } from '@/types/common'
import styles from './Button.module.scss'

function Button({ children, width, ...props }: IButton) {
  return (
    <button className={styles.container} style={{ width }} {...props}>
      {children}
    </button>
  )
}

export default React.memo(Button)
