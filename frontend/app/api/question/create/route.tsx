import prisma from '@/prisma/client'
import { IHashtag } from '@/types/community'
import { AxiosError } from 'axios'
import { NextRequest, NextResponse } from 'next/server'

export async function POST(req: NextRequest) {
  const { tags, title, desc }: { tags: IHashtag[]; title: string; desc: string } = await req.json()

  try {
    const tagquery = tags.map(tag => {
      const title = tag.title.toLowerCase()
      return prisma.hashtag.upsert({
        where: {
          title,
        },
        update: {},
        create: {
          title,
        },
      })
    })

    const hashtags = await prisma.$transaction([...tagquery])

    try {
      const newQuestion = await prisma.question.create({
        data: {
          memberId: 1,
          memberName: '김싸피',
          title,
          desc,
        },
      })

      if (newQuestion) {
        const qt = hashtags.reduce((arr: { questionId: number; hashtagId: number }[], now) => {
          return [...arr, { questionId: newQuestion.id, hashtagId: now.id }]
        }, [])

        try {
          await prisma.questionhash.createMany({
            data: qt,
          })
          return NextResponse.json({ message: newQuestion }, { status: 201 })
        } catch (err) {
          console.log(`태그 연결 에러: ${err}`)
          throw new Error('태그 연결 에러')
        }
      }
    } catch (err) {
      console.log(`글작성 에러: :${err}`)
      throw new Error('작성 에러')
    }
  } catch (err) {
    console.log(`해시태그 에러: ${err}`)
    throw new Error('해시태그 에러')
  }
}
