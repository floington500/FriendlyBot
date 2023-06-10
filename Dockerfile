FROM maven

WORKDIR /app

# env file containing bot token
COPY .env ./

# copy pom.xml and resolve dependencies
COPY pom.xml .
RUN mvn dependency:resolve

# copy source files then build jar
COPY /src ./src/
RUN mvn package

# pass in program arguements here if any
CMD ["java", "-jar", "target/FriendlyBot-1.2.4.jar"]
