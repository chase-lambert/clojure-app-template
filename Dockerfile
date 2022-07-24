# syntax = docker/dockerfile:1.2
FROM clojure:latest AS build

WORKDIR /
COPY . /

RUN npm install
RUN npm run release 
RUN clojure -X:uberjar :jar TODO.jar

FROM clojure:latest

COPY --from=build /target/TODO-standalone.jar /TODO/TODO-standalone.jar

EXPOSE $PORT

ENTRYPOINT exec java $JAVA_OPTS -jar /TODO/TODO-standalone.jar
