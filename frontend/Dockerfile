#
# Dockerfile
#


# Install dependencies only when needed
FROM node:20-alpine AS deps
RUN apk add --no-cache libc6-compat


RUN corepack enable && corepack prepare pnpm@latest --activate
WORKDIR /app


# Install dependencies based on the preferred package manager
# COPY package.json pnpm-lock.yaml* ./
# RUN rm -rf ./.next/cache
# RUN \
#   if [ -f pnpm-lock.yaml ]; then --mount=type=cache,id=pnpm,target=/pnpm/store pnpm install --frozen-lockfile; \
#   else echo "Lockfile not found." && exit 1; \
#   fi
COPY package.json pnpm-lock.yaml ./
RUN pnpm i --frozen-lockfile

# Rebuild the source code only when needed
FROM node:20-alpine AS builder
WORKDIR /app

COPY --from=deps /app/node_modules ./node_modules
COPY . .

# Next.js collects completely anonymous telemetry data about general usage.
# Learn more here: https://nextjs.org/telemetry
# Uncomment the following line in case you want to disable telemetry during the build.
# ENV NEXT_TELEMETRY_DISABLED 1
WORKDIR /app
RUN pnpm build

# If using npm comment out above and use below instead
# RUN npm run build

# Production image, copy all the files and run next
FROM node:20-alpine AS runner
WORKDIR /app

ENV NODE_ENV production
# Uncomment the following line in case you want to disable telemetry during runtime.
# ENV NEXT_TELEMETRY_DISABLED 1

RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 nextjs

COPY --from=builder /app/public ./public

# Automatically leverage output traces to reduce image size
# https://nextjs.org/docs/advanced-features/output-file-tracing
COPY --from=builder --chown=nextjs:nodejs /app/.next/standalone ./
COPY --from=builder --chown=nextjs:nodejs /app/.next/static ./.next/static

###
### aws-lambda-adapter
###
COPY --from=public.ecr.aws/awsguru/aws-lambda-adapter:0.6.0 /lambda-adapter /opt/extensions/lambda-adapter
###
###
###

USER nextjs

EXPOSE 3000

ENV PORT 3000

CMD ["node", "server.js"]