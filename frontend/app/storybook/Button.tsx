import { PropsWithChildren } from 'react'
import './button.scss'
interface ButtonProps extends PropsWithChildren {
  disabled?: boolean
  variant: 'primary' | 'dashed'
  onClick?: () => void
}

export default function Button({ variant, disabled, children, onClick }: ButtonProps) {
  return (
    <button variant={variant} disabled={disabled} onClick={onClick} className={'book-button'}>
      {children}
    </button>
  )
}
