# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/blog.jar app.jar


EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]