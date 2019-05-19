FROM java:8
VOLUME /tmp
EXPOSE 8081
ADD target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
RUN sh -c 'touch /app.jar'