FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/backend_it_hero_2-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/task_manager


EXPOSE 8090

ENTRYPOINT ["java","-jar","/app.jar"]