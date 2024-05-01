import { learningData } from '@/components/learning/LearningList/learningData'
import { NextResponse } from 'next/server'

export async function GET() {
  return NextResponse.json({ learningList: learningData }, { status: 200 })
}
