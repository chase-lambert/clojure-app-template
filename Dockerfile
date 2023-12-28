# syntax = docker/dockerfile:1.2
FROM clojure:latest

WORKDIR /
COPY . /

ENV NODE_VERSION=19.6.0
# RUN apt install -y curl
RUN apt-get update \
    && apt-get install -y curl
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.3/install.sh | bash
ENV NVM_DIR=/root/.nvm
RUN . "$NVM_DIR/nvm.sh" && nvm install ${NODE_VERSION}
RUN . "$NVM_DIR/nvm.sh" && nvm use v${NODE_VERSION}
RUN . "$NVM_DIR/nvm.sh" && nvm alias default v${NODE_VERSION}
ENV PATH="/root/.nvm/versions/node/v${NODE_VERSION}/bin/:${PATH}"

RUN npm install
RUN npm run release 
RUN clojure -T:build uber

EXPOSE $PORT

ENTRYPOINT exec java $JAVA_OPTS -jar target/TODO.jar
