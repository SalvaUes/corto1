FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

# Copy maven wrapper files first
COPY .mvn/ .mvn/
COPY mvnw mvnw
COPY pom.xml .

# Convert line endings and set permissions
RUN sed -i 's/\r$//' mvnw && \
    chmod +x mvnw && \
    find .mvn/wrapper -type f -exec sed -i 's/\r$//' {} \;

# Download dependencies (this step will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src/ src/

# Build the application
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]