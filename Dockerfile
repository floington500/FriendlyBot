# specify the base image to use
FROM openjdk:17

# set working dir inside container to /app
WORKDIR /app

# in order to copy the jar, you must build it first using `mvn package` or `mvn install`
COPY target/FriendlyBot-1.2.4.jar .

# if you want to change any of the program arguements such as the token or buffersize, pass them in here
CMD ["java", "-jar", "FriendlyBot-1.2.4.jar"]
