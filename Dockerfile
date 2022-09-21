FROM maven:3.8.3-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17-jdk-slim
ARG JAR_FILE=*.jar
COPY --from=build /home/app/target/${JAR_FILE} chatilo.jar
ENTRYPOINT ["java", "-jar", "chatilo.jar"]