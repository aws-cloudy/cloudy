import prisma from '@/prisma/client'
import { NextResponse } from 'next/server'

export async function GET() {
  const hashList = await prisma.hashtag.findMany({
    orderBy: [{ title: 'asc' }],
  })

  return NextResponse.json({ hashList })
}
