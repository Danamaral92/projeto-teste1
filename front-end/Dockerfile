FROM node:24-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine AS production

COPY --from=build /app/dist /usr/share/nginx/html

ARG NGINX_PORT

ENV NGINX_PORT=$NGINX_PORT

ARG BASE_URL

ENV BASE_URL=$BASE_URL

COPY nginx.conf /etc/nginx/templates/default.conf.template


CMD ["/bin/sh", "-c", "envsubst '$NGINX_PORT' < /etc/nginx/templates/default.conf.template > /etc/nginx/conf.d/default.conf && exec nginx -g 'daemon off;'"]
