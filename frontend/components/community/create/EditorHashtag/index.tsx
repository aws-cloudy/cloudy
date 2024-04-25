'use client'

import styles from './EditorHashtag.module.scss'

function EditorHashtag() {
  return (
    <div className={styles.container}>
      <button className={styles.nodata} type="button">
        + 태그추가
      </button>
    </div>
  )
}

export default EditorHashtag
