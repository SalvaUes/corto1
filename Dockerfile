FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw mvnw
COPY pom.xml .


RUN sed -i 's/\r$//' mvnw && \
    chmod +x mvnw && \
    find .mvn/wrapper -type f -exec sed -i 's/\r$//' {} \;

    



RUN ./mvnw dependency:go-offline

COPY src/ src/



RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]