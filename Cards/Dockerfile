# Start with base image containing Java runtime.
FROM openjdk:17-jdk-slim

# Information around who maintains the image
MAINTAINER sachinkansal

# Add Application jar to the image
COPY target/Cards-0.0.1-SNAPSHOT.jar Cards-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "Cards-0.0.1-SNAPSHOT.jar"]