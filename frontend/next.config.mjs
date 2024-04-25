/** @type {import('next').NextConfig} */
const nextConfig = {
  sassOptions: {
    includePaths: ['styles'],
  },
  images: {
    formats: ['image/avif', 'image/webp'],
  },
}

export default nextConfig
