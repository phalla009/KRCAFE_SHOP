# Build stage with Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Build the WAR file
RUN mvn clean package -DskipTests

# Package stage
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built WAR
COPY --from=build /app/target/coffee-shop-html-telegram-bot-0.0.1-SNAPSHOT.war app.war

# Copy application.properties (optional if you want default config inside image)
# COPY src/main/resources/application.properties ./application.properties

# Expose port
EXPOSE 8080

# Run the WAR
ENTRYPOINT ["java","-jar","app.war"]
