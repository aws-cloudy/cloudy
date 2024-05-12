export const res = {
  questionList: [
    {
      id: 4,
      memberId: '0498fd2c-a031-7096-02b6-0dbcbb40c9f0',
      memberName: '김싸피',
      title: 'test ',
      desc: 'test1',
      hit: 0,
      createdAt: '2024-05-03T02:04:36.546Z',
      checkedId: null,
      _count: {
        answers: 1,
      },
      hashtags: [
        {
          hashtag: {
            id: 5,
            title: 'test',
          },
        },
        {
          hashtag: {
            id: 6,
            title: 'db',
          },
        },
      ],
    },
    {
      id: 1,
      memberId: '0498fd2c-a031-7096-02b6-0dbcbb40c9f0',
      memberName: '김싸피',
      title: 'test',
      desc: "I'm trying to read words in a text file. If a word is duplicated I want to increase the Words.frequency +=1 and not add the word to lexicon. It's for an assignment, so nothing specific, just point me in the right direction.\n\n```\nclass Words:\n     def __init__(self, word, frequency, neighbours ):\n         self.word = word \n        self.frequency = frequency \n        self.neighbours = neighbours  \n\ndef read_data(lexicon, filename):\n   file1 = open(filename, 'r')\n   f = file1.readlines()\n   mot = Words\n\n     for line in f:\n       # lexicon.append(line.strip())\n       for word in line.split():\n           word = ''.join([ch for ch in word if ch.isalpha()])\n           mot = word.lower()\n                                 if any(Words.word == word for Words in lexicon):\n                                       #if the word is a duplicate then find the instance of that word in lexicon and frequency += 1\n                                 else:\n                                       lexicon.append(Words(mot.lower(), 0, None))\n```\n\n\nI've tried using list.index(x) to find the lexicon element where said word is, but it keeps getting below error:\n\nAttributeError: type object 'Words' has no attribute 'word'\nI've tried enumerating through it, but that doesn't work either\n\n![alt text](https://ucirshljogphrxuljrju.supabase.co/storage/v1/object/public/cloudy_image/8919a23c-67cd-4ab6-b348-d453915b6c40.jpeg)",
      hit: 0,
      createdAt: '2024-05-03T00:16:52.786Z',
      checkedId: 1,
      _count: {
        answers: 1,
      },
      hashtags: [
        {
          hashtag: {
            id: 1,
            title: '보안/자격',
          },
        },
      ],
    },
  ],
  count: 2,
  isLast: false,
}
