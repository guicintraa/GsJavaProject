# syntax=docker/dockerfile:1

# ====== BUILD ======
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace/app

COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
# força fast-jar
RUN mvn -DskipTests -Dquarkus.package.type=fast-jar clean package

# ====== RUNTIME ======
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia TUDO que o fast-jar gera (inclui quarkus-run.jar, lib/, app/, quarkus/)
COPY --from=build /workspace/app/target/quarkus-app/ /app/

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
EXPOSE 8080

# caminho correto do jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/quarkus-run.jar"]
