import { IChatBotList } from '@/types/chatbot'

export const chatBotList: IChatBotList = {
  main: {
    sub: '',
    name: '챗봇',
    msg: '궁금한 게 있으신가요? 챗봇에게 물어보세요!',
    image: '',
    type: -1,
  },
  cla: {
    sub: '척척박사',
    name: '클라',
    msg: '학습 중 궁금한 게 있을 땐 클라와 대화해보세요',
    image: '/img/chatbot/cla.png',
    type: 0,
  },
  oudy: {
    sub: '학습 추천 도우미',
    name: '우디',
    msg: '학습을 추천받고 싶다면 우디와 대화해보세요',
    image: '/img/chatbot/oudy.png',
    type: 1,
  },
  ama: {
    sub: '비용 산정 도우미',
    name: '아마',
    msg: '서비스 비용이 궁금하다면 아마와 대화해보세요',
    image: '/img/chatbot/ama.png',
    type: 2,
  },
  john: {
    sub: '로드맵 추천 도우미',
    name: '존',
    msg: '로드맵을 추천받고 싶다면 존과 대화해보세요',
    image: '/img/chatbot/john.png',
    type: 3,
  },
}
