FROM amazoncorretto:11-alpine-jdk
COPY http-server/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]