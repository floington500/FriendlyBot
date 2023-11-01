FROM maven

WORKDIR /app

SHELL ["/bin/bash", "-c"]

# create env file
COPY scripts/setup.sh .
RUN ./setup.sh

# copy pom.xml
COPY pom.xml .

# resolve missing dependencies
RUN mvn dependency:resolve

# copy source files then build jar
COPY /src ./src/
RUN mvn package

# pass in program arguements here if any
CMD ["java", "-jar", "target/friendly-bot-1.2.4.jar"]
