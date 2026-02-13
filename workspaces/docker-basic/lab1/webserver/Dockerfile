FROM alpine

RUN apk update  \
&& apk add openjdk21-jre

ENV JAVA_HOME="/usr/lib/jvm/java-21-openjdk"

WORKDIR /work

COPY bootmywebref2.jar ./bootmywebref2.jar

CMD ["java", "-jar", "/work/bootmywebref2.jar"]

EXPOSE 8080