FROM maven:3.8-eclipse-temurin-11 AS builder

WORKDIR /app

COPY bookstoreApplication/pom.xml .
COPY bookstoreApplication/.mvn ./.mvn

RUN mvn dependency:go-offline -B

COPY bookstoreApplication/src ./src

RUN mvn package -DskipTests -B

FROM eclipse-temurin:11-jre-alpine

RUN apk add --no-cache curl

WORKDIR /app

COPY --from=builder /app/target/*.war app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.war"]
