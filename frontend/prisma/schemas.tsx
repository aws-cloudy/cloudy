import { z } from 'zod'

export const HashSchema = z.object({
  id: z.number(),
  title: z
    .string()
    .min(1, { message: '1글자 이상 입력해주세요.' })
    .max(20, { message: '해시태그는 20자를 초과할 수 없습니다.' }),
})

export const CreateHashtag = HashSchema.omit({ id: true })

export const QuestionSchema = z.object({
  id: z.number(),
  memberId: z.number(),
  memberName: z.string(),
  title: z
    .string()
    .min(1, { message: '1글자 이상 입력해주세요.' })
    .max(30, { message: '제목은 30자를 초과할 수 없습니다.' }),
  desc: z
    .string()
    .min(1, { message: '1글자 이상 입력해주세요.' })
    .max(3000, { message: '본문은 3000자를 초과할 수 없습니다.' }),
  createdAt: z.date(),
})

export const CreateQuestion = QuestionSchema.omit({ id: true, createdAt: true })
