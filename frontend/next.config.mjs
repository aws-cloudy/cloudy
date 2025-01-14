/** @type {import('next').NextConfig} */
const nextConfig = {
  sassOptions: {
    includePaths: ['styles'],
  },
  images: {
    remotePatterns: [
      { protocol: 'https', hostname: 'explore.skillbuilder.aws' },
      { protocol: 'https', hostname: 'cdn.inflearn.com' },
      { protocol: 'https', hostname: 'rattle-stock-d32.notion.site' },
      { protocol: 'https', hostname: 'encrypted-tbn0.gstatic.com' },
      { protocol: 'https', hostname: 'media.licdn.com' },
      { protocol: 'https', hostname: 'devio2023-media.developers.io' },
      { protocol: 'https', hostname: 'd1tlzifd8jdoy4.cloudfront.net' },
      { protocol: 'https', hostname: 'i.ytimg.com' },
      { protocol: 'https', hostname: 'dev.classmethod.jp' },
      { protocol: 'https', hostname: 'd1.awsstatic.com' },
      { protocol: 'https', hostname: 'digitalcloud.training' },
      { protocol: 'https', hostname: 'i.pcmag.com' },
      { protocol: 'http', hostname: 'localhost' },
    ],
    minimumCacheTTL: 31536000,
    formats: ['image/avif', 'image/webp'],
  },
  reactStrictMode: false,
  output: 'standalone',
  async headers() {
    return [
      {
        source: '/api/:path*',
        headers: [
          { key: 'Access-Control-Allow-Credentials', value: 'true' },
          { key: 'Access-Control-Allow-Origin', value: '*' },
          {
            key: 'Access-Control-Allow-Methods',
            value: 'GET,OPTIONS,PATCH,DELETE,POST,PUT',
          },
          {
            key: 'Access-Control-Allow-Headers',
            value:
              'X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version',
          },
        ],
      },
    ]
  },
  rewrites() {
    return [
      {
        source: '/cloudy-api/:path*',
        destination: 'https://3m8faj87ji.execute-api.ap-northeast-2.amazonaws.com/prod/api/v1/:path*',
      },
      {
        source: '/cloudy-search-api/:path*',
        destination: 'https://o5u2oz980k.execute-api.ap-northeast-2.amazonaws.com/prod/api/v1/:path*',
      },
      {
        source: '/cloudy-chat-api/:path*',
        destination: 'https://k10s207.p.ssafy.io/api/v1/:path*',
      },
    ]
  },
}

export default nextConfig
