/** @type {import('next').NextConfig} */
const nextConfig = {
  sassOptions: {
    includePaths: ['styles'],
  },
  images: {
    domains: ['cdn.inflearn.com', 'localhost'],
    formats: ['image/avif', 'image/webp'],
  },
  reactStrictMode: false,
}

export default nextConfig
