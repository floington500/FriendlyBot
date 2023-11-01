FROM maven

WORKDIR /app

# copy env file if present
RUN if [ -f .env ]; then \
      COPY .env /app/.env; \
    else \
        echo ${{ secrets.BOT_TOKEN }} > .env \
    fi

# copy pom.xml
COPY pom.xml .

# resolve missing dependencies
RUN mvn dependency:resolve

# copy source files then build jar
COPY /src ./src/
RUN mvn package

# pass in program arguements here if any
CMD ["java", "-jar", "target/friendly-bot-1.2.4.jar"]
