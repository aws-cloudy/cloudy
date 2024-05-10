'use client'
import React, { useEffect, useState } from 'react'
import styles from './DetailTop.module.scss'
import { BsChat, BsBookmark, BsBookmarkFill } from 'react-icons/bs'
import Avatar from '@/components/common/Avatar'
import { IRoadmapCard } from '@/types/roadmap'
import { deleteBookmark, getBookmarks, postBookmark } from '@/apis/bookmark'
import { useSession } from 'next-auth/react'

const DetailTop = ({ data }: { data: IRoadmapCard }) => {
  const { data: session, status } = useSession()
  const [clickMark, setClickMark] = useState(false)
  const [bookmarkId, setBookmarkId] = useState<number | null>(null)

  useEffect(() => {
    const fetchBookmarks = async () => {
      if (session?.user.id) {
        const bookmarks = await getBookmarks(session.user.id)
        const roadmapBookmark = bookmarks.roadmaps.find(
          (mark: { roadmapId: number }) => mark.roadmapId === data.roadmapId,
        )

        if (roadmapBookmark) {
          setClickMark(true)
          setBookmarkId(roadmapBookmark.bookmarkId)
        } else {
          setClickMark(false)
          setBookmarkId(null)
        }
      }
    }
    fetchBookmarks()
  }, [session, data.roadmapId])

  const handleMarkClear = async () => {
    if (bookmarkId === null) return

    try {
      await deleteBookmark(bookmarkId)
      setClickMark(false)
      setBookmarkId(null)
    } catch (error) {
      console.error('스크랩 해제 실패하였습니다.', error)
    }
  }

  const handleMarkSelect = async () => {
    try {
      await postBookmark(data.roadmapId)
      setClickMark(true)
    } catch (error) {
      console.error('스크랩 실패하였습니다.', error)
    }
  }

  return (
    <>
      <div className={styles.tags}>
        <div>#{data.job}</div>
        <div>#{data.service}</div>
      </div>
      <div className={styles.title}>
        {data.title}
        {clickMark ? (
          <BsBookmarkFill
            className={styles.bookmark}
            onClick={handleMarkClear}
            role="button"
            data-testid="scrap-icon"
          />
        ) : (
          <BsBookmark className={styles.bookmark} onClick={handleMarkSelect} role="button" data-testid="unscrap-icon" />
        )}
      </div>
      <div className={styles.profile}>
        <div className={styles.profileImage}>
          <Avatar Icon={BsChat} size="small" />
        </div>
        <div className={styles.name}>클라우디</div>
      </div>
    </>
  )
}

export default DetailTop
