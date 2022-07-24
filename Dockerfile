# syntax = docker/dockerfile:1.2
FROM clojure:latest AS build

WORKDIR /
COPY . /

RUN npm install
RUN npm run release 
RUN clj -T:build uber

FROM clojure:latest

COPY --from=build /target/TODO-standalone.jar /TODO/TODO-standalone.jar

EXPOSE $PORT

ENTRYPOINT exec java $JAVA_OPTS -jar /TODO/TODO-standalone.jar
