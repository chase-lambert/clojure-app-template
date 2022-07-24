# syntax = docker/dockerfile:1.2
FROM clojure:latest AS build

WORKDIR /
COPY . /

RUN apt install -y curl
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
RUN export NVM_DIR="$([ -z "${XDG_CONFIG_HOME-}" ] && printf %s "${HOME}/.nvm" || printf %s "${XDG_CONFIG_HOME}/nvm")"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" # This loads nvm
RUN nvm install npm 
RUN npm install
RUN npm run release 
RUN clj -T:build uber

FROM clojure:latest

COPY --from=build /target/TODO-standalone.jar /TODO/TODO-standalone.jar

EXPOSE $PORT

ENTRYPOINT exec java $JAVA_OPTS -jar /TODO/TODO-standalone.jar
