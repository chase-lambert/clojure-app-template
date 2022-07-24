# syntax = docker/dockerfile:1.2
FROM node:latest AS node

WORKDIR /
COPY . /

RUN npm install
RUN npm run release 

FROM clojure:latest AS clojure

RUN clj -T:build uber

COPY --from=clojure /target/TODO-standalone.jar /TODO/TODO-standalone.jar

EXPOSE $PORT

ENTRYPOINT exec java $JAVA_OPTS -jar /TODO/TODO-standalone.jar
