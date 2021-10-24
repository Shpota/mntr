FROM node:16.12.0-alpine3.14 as client-builder
COPY src /app/src/
COPY public /app/public
COPY yarn.lock /app/
COPY package.json /app/
COPY .env.production /app/
WORKDIR /app
RUN yarn install && yarn build --profile

FROM nginx:1.21.1
COPY nginx.conf /etc/nginx/conf.d/default.conf
RUN rm -rf /usr/share/nginx/html/*
COPY --from=client-builder /app/build /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]