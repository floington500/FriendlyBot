# specify the base image to use
FROM maven:3.8.4-openjdk-17

WORKDIR /app

# copy the .env file
COPY .env ./

COPY pom.xml .
RUN mvn dependency:resolve

COPY /src ./src/
RUN mvn package -DskipTests

# pass in program arguements here if needed
CMD ["java", "-jar", "target/FriendlyBot-1.2.4.jar"]
