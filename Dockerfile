# syntax=docker/dockerfile:1

# ===== BUILD (JDK 21) =====
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace/app

# cache de dependências
COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

# código-fonte e empacote em fast-jar
COPY src ./src
RUN mvn -DskipTests -Dquarkus.package.type=fast-jar clean package

# ===== RUNTIME (JRE 21) =====
FROM eclipse-temurin:21-jre
WORKDIR /app

# copia TUDO do quarkus-app (inclui quarkus-run.jar, lib/, app/, quarkus/)
COPY --from=build /workspace/app/target/quarkus-app/ /app/

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
EXPOSE 8080

# caminho correto do jar no fast-jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/quarkus-run.jar"]
