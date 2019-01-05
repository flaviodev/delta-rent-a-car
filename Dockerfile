FROM openjdk:8-jre-alpine

ENV JAVA_OPTS="-Xmx256m -Xss512k -agentlib:jdwp=transport=dt_socket,address=5009,server=y,suspend=n"

ENV LANG en_US.UTF-8
ENV TZ=America/Sao_Paulo

VOLUME /tmp
ADD /target/*.jar /delta-rent-a-car.jar

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /delta-rent-a-car.jar" ]
