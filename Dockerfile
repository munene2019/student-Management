# Use an official OpenJDK runtime as a parent image

FROM openjdk:17-jdk-alpine


# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/*.jar /app/app.jar

# Specify the command to run on container start
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
