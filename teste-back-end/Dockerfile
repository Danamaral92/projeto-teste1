FROM maven:3.9.5-eclipse-temurin-21 AS builder

WORKDIR /app

COPY ../back-end/pom.xml .
RUN mvn dependency:go-offline

COPY ../back-end/src ./src

RUN mvn clean package -DskipTests

# Runtime
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    && rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/target/*.jar app.jar

ARG SERVER_PORT

EXPOSE $SERVER_PORT

HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:$SERVER_PORT/api || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]






#FROM maven:3.6-openjdk-17
#
#LABEL authors="daniel"
#LABEL description="Projeto Teste 1"
#
#WORKDIR /app
#COPY . /app
#
#RUN mvn clean install -Dmaven.test.skip
#CMD ["java", "-jar", "target/projeto-teste1-0.0.1-SNAPSHOT.jar"]
#
