# Step 1: Build the application using Maven
FROM maven:3.9.9-openjdk-17-slim as build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src /app/src

# Compile and package the application
RUN mvn clean install -DskipTests

# Step 2: Create the final image using the OpenJDK runtime environment
FROM openjdk:17-jdk-slim

# Set the working directory for the running app
WORKDIR /app

# Copy the jar file from the build image
COPY --from=build /app/target/your-app-name.jar /app/your-app-name.jar

# Expose the port your app is running on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/your-app-name.jar"]
