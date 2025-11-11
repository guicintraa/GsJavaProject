# FutureSkills API (Quarkus)

API oficial da GS – alinhada ao **Oracle** conforme `CRIA.sql`/`APAGA.sql`/`CARGA.sql`/`CONSULTAS.sql`.

## Requisitos
- JDK 21
- Maven 3.9+
- Banco Oracle acessível e com o esquema criado (rodar `db/CRIA.sql`, depois `db/CARGA.sql`)
- Variáveis de ambiente: `DB_URL`, `DB_USER`, `DB_PASS`
  - Ex.: `jdbc:oracle:thin:@//host:1521/XEPDB1`

> O driver Oracle é necessário em runtime. A extensão `quarkus-jdbc-oracle` está no pom.

## Rodar local
```bash
export DB_URL=jdbc:oracle:thin:@//host:1521/XEPDB1
export DB_USER=usuario
export DB_PASS=senha

./mvnw quarkus:dev
# ou
mvn clean package -DskipTests
java -jar target/quarkus-app/quarkus-run.jar
```

## Endpoints
- `POST /api/usuarios` – cria usuário `{nome, email, interesse}`
- `GET  /api/usuarios`
- `GET  /api/usuarios/{id}`
- `POST /api/recomendacao/{idUsuario}` – gera & grava recomendação + cluster + log
- `GET  /api/recomendacao/usuario/{idUsuario}` – histórico de recomendações
- `GET  /api/cluster/usuario/{idUsuario}` – cluster atual
- `GET  /api/health`

### Exemplo (Insomnia/curl)
```bash
curl -X POST http://localhost:8080/api/usuarios -H "Content-Type: application/json" -d '{"nome":"Ana","email":"ana@gs.com","interesse":"data"}'
curl -X POST http://localhost:8080/api/recomendacao/1
curl http://localhost:8080/api/recomendacao/usuario/1
```

## Docker (JVM/fast-jar)
```bash
mvn clean package -DskipTests
docker build -t futureskills-api:1 .
docker run -e DB_URL=$DB_URL -e DB_USER=$DB_USER -e DB_PASS=$DB_PASS -p 8080:8080 futureskills-api:1
```

## Scripts SQL
- Em `src/main/resources/db/`: `CRIA.sql`, `APAGA.sql`, `CARGA.sql`, `CONSULTAS.sql`


## Deploy em Render/Railway (Docker)
1. Confirme que o repo contém `pom.xml`, `src/`, `Dockerfile` e `.dockerignore`.
2. O Dockerfile faz o build (fast-jar) e executa o Quarkus.
3. No provedor escolha **Docker** e deixe o Start Command em branco.
4. Porta exposta: **8080**.
5. Variáveis de ambiente comuns (exemplos):
   - `QUARKUS_HTTP_PORT=8080` (opcional)
   - `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` para o Oracle, se aplicável.
