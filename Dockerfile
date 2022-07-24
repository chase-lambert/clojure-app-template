# syntax = docker/dockerfile:1.2
FROM clojure:latest

WORKDIR /
COPY . /

RUN apt install -y curl
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
RUN source ~/.bashrc
RUN nvm install node
RUN npm install
RUN npm run release 
RUN clj -T:build uber

COPY --from=clojure /target/TODO-standalone.jar /TODO/TODO-standalone.jar

EXPOSE $PORT

ENTRYPOINT exec java $JAVA_OPTS -jar /TODO/TODO-standalone.jar
