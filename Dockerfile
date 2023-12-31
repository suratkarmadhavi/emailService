# Use a lightweight base image for Maven
FROM openjdk:17-jdk-slim
 
# Set the working directory inside the container
WORKDIR /app

# Copy the source code into the container
COPY . .

RUN ls

RUN pwd

# Copy the compiled JAR from the build stage into the container
# COPY /app/target/emailapi-0.0.1-SNAPSHOT.jar app1.jar

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "target/emailapi-0.0.1-SNAPSHOT.jar"]