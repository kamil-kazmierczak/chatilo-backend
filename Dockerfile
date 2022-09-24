FROM maven:3.8.3-openjdk-17-slim AS chatilo-backend-image
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17-jdk-slim
ARG JAR_FILE=*.jar
COPY --from=chatilo-backend-image /home/app/target/${JAR_FILE} chatilo-backend.jar
ENTRYPOINT ["java", "-jar", "chatilo-backend.jar"]