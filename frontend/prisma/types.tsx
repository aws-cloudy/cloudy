import { Answer, Hashtag, Question } from '@prisma/client'

export interface IRQuestion extends Question {
  hashtags: {
    hashtag: Hashtag
  }[]
  _count: {
    answers: number
  }
}

export interface IPrismaError {
  error?: {
    status: number
    code: string
    msg: string
  }
}

export interface IfetchQuestions extends IPrismaError {
  questions: IRQuestion[]
  count: number
}

export interface IcreateAnswer extends IPrismaError {
  answer?: Answer
}

export interface IcreateHashtag extends IPrismaError {
  hashtags?: Hashtag[]
}

export interface IcreateQuestion extends IPrismaError {
  question?: Question
}

export interface IFetchMyAnswers extends IPrismaError {
  answers: Answer[]
  count: number
}
