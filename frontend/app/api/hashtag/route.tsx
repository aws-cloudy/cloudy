import { fetchHashtags } from '@/prisma/data/hashtag'
import { NextResponse } from 'next/server'

export async function GET() {
  const hashtagQuery = await fetchHashtags()

  if (hashtagQuery.error) {
    return NextResponse.json({ error: hashtagQuery.error }, { status: hashtagQuery.error.status })
  }

  return NextResponse.json({ hashtags: hashtagQuery.hashtags }, { status: 200 })
}
