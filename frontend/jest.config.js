const nextJest = require('next/jest')

// Next.js 에게 경로를 제공
const createJestConfig = nextJest({ dir: './' })

// jest 에서 설정하고 싶은 옵션을 여기서 작성하면 된다.
const customJestConfig = {
  setupFilesAfterEnv: ['<rootDir>/jest.setup.ts'],
  testEnvironment: 'jest-environment-jsdom',
  // testResultsProcessor 추가
  testResultsProcessor: "jest-junit",
}

// createJestConfig는 next/jest가 비동기식인 Next.js 구성을 로드할 수 있도록 이러한 방식으로 내보내짐
module.exports = createJestConfig(customJestConfig)
