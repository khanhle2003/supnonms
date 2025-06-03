FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY user-service/pom.xml ./user-service/
COPY user-service/src ./user-service/src
RUN mvn clean package -DskipTests -f user-service/pom.xml

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/user-service/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"] 