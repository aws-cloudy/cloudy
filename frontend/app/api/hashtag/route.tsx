import { fetchHashtags } from '@/prisma/data/hashtag'
import { NextResponse } from 'next/server'

export async function GET() {
  const hashtags = await fetchHashtags()

  return NextResponse.json({ hashtags }, { status: 200 })
}
