/** @type {import('next-sitemap').IConfig} */

module.exports = {
  siteUrl: 'https://www.aws-cloudy.com',
  generateRobotsTxt: true, // robots.txt generate 여부 (자동생성 여부)
  sitemapSize: 5000,
  changefreq: 'daily',
  priority: 1, // 페이지 주소 우선순위 (검색엔진에 제공됨, 우선순위가 높은 순서대로 크롤링함)
  exclude: [],
  robotsTxtOptions: {
    policies: [
      {
        userAgent: '*',
        allow: '/',
        disallow: [
          '/api', // api로 시작하는 페이지 주소 크롤링 금지
        ],
      },
    ],
  },
}
