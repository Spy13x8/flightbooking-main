# Use a base image with Java
FROM openjdk:17-jdk-slim as build

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container (Replace with your JAR file name)
COPY target/flightBooking-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will run on (default is 8080)
EXPOSE 9090

# Command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]