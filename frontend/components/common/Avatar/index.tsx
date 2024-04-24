import { IconType } from 'react-icons'
import styles from './Avatar.module.scss'

type Props = {
  Icon: IconType
  size?: 'big' | 'mid' | 'small'
}

function Avatar({ Icon, size }: Props) {
  const sizes = () => {
    switch (size) {
      case 'big':
        return { ico: '80px', font: '3em' }
      case 'mid':
        return { ico: '50px', font: '2em' }
      case 'small':
        return { ico: '30px', font: '1em' }
      default:
        return { ico: '50px', font: '2em' }
    }
  }

  return (
    <div className={styles.container} style={{ width: sizes().ico, height: sizes().ico }}>
      <Icon className={styles.icon} style={{ fontSize: sizes().font }} />
    </div>
  )
}

export default Avatar
