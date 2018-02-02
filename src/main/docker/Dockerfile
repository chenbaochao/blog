FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD docker_spring_boot.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker", "-jar", "/app.jar" ]