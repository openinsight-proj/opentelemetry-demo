FROM adoptopenjdk/openjdk8:alpine

RUN rm -rf /etc/localtime \
&& ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

WORKDIR /app
ENV JAVA_OPTS ''
COPY target/kpanda.jar  /app/
ENTRYPOINT java $JAVA_OPTS -jar kpanda.jar