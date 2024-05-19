import { z } from 'zod'

export const HashSchema = z.object({
  id: z.number().nullable(),
  title: z
    .string()
    .min(1, { message: '1글자 이상 입력해주세요.' })
    .max(20, { message: '해시태그는 20자를 초과할 수 없습니다.' }),
})

export const CreateHashtag = HashSchema

export const QuestionSchema = z.object({
  id: z.number(),
  memberId: z.string(),
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
  checkedId: z.number(),
})

export const CreateQuestion = QuestionSchema.omit({ id: true, createdAt: true, checkedId: true })
export const UpdateQuestion = QuestionSchema.omit({
  createdAt: true,
  checkedId: true,
  memberId: true,
  memberName: true,
})
export const DeleteQuestion = QuestionSchema.pick({ id: true })

export const QHSchema = z.object({
  questionId: z.number(),
  hashtagId: z.number(),
})

export const CreateQH = QHSchema

export const AnswerSchema = z.object({
  id: z.number(),
  memberId: z.string(),
  memberName: z.string(),
  createdAt: z.date(),
  postId: z.number(),
  desc: z
    .string()
    .min(1, { message: '1글자 이상 입력해주세요.' })
    .max(1000, { message: '본문은 1000자를 초과할 수 없습니다.' }),
})

export const CreateAnswer = AnswerSchema.omit({ id: true, createdAt: true })
export const DeleteAnswer = AnswerSchema.pick({ id: true })

export const QuestionImageSchema = z.object({
  path: z.string(),
  url: z.string(),
  questionId: z.number(),
})

export const CreateQuestionImage = QuestionImageSchema.omit({ questionId: true })
export const DeleteQuestionImage = QuestionImageSchema.pick({ questionId: true })
