# Build
FROM gradle:8.11.1-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test

# Run
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]