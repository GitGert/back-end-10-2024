# Use an official OpenJDK 21 image as the base image
FROM eclipse-temurin:21-jdk AS base

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY out/artifacts/veebipood_jar/veebipood.jar app.jar


COPY target/veebipood-0.0.1-SNAPSHOT.jar app.jar


# Expose the application port (replace with your application's port if different)
EXPOSE 8080

# Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]