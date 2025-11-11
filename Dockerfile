# syntax=docker/dockerfile:1

# ===== BUILD =====
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace/app

# Copia apenas o pom para cache de dependências
COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

# Copia o código e empacota em FAST-JAR
COPY src ./src
RUN mvn -DskipTests -Dquarkus.package.type=fast-jar clean package

# ===== RUNTIME =====
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o layout gerado pelo FAST-JAR
COPY --from=build /workspace/app/target/quarkus-app/lib/     /app/lib/
COPY --from=build /workspace/app/target/quarkus-app/app/     /app/app/
COPY --from=build /workspace/app/target/quarkus-app/quarkus/ /app/quarkus/

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/quarkus/quarkus-run.jar"]
