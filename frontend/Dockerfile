FROM node:20-alpine

WORKDIR /usr/src/app

COPY package*.json ./
COPY pnpm-lock.yaml ./

RUN corepack enable
RUN corepack prepare pnpm@latest-9 --activate
RUN pnpm i
RUN pnpm generate

COPY . .

EXPOSE 3000

RUN pnpm run build 

CMD [ "pnpm", "run", "start" ]
