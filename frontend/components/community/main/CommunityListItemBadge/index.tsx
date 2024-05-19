import styles from './CommunityListItemBadge.module.scss'

function CommunityListItemBadge({ checked }: { checked: boolean }) {
  const content = checked ? '답변완료' : '미해결'
  const { container, check, none } = styles

  return <div className={`${container} ${checked ? check : none}`}>{content}</div>
}

export default CommunityListItemBadge
