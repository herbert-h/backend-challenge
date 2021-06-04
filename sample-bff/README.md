# Sample BFF

## Service Specs
- Kotlin with Spring Web Flux
- Gradle
- Onion Architecture
- Domain Driven Design

## Must Have
- [x] Route for retrive product for example `GET /products`
- [x] Route may receive header `X-USER_ID`
- [x] Get discount from other service using gRPC
- [x] Be resilient and keep up even without discount service
- [x] Docker

## Nice to Have
- [x] Route for add product `POST /products`
- [x] Route for add user `POST /users`
- [x] Route for get users `GET /users`
- [x] Unit tests
- [x] Integration tests

# Improvements
- [ ] Organize application yaml
- [ ] Use circuit breaker framework
- [ ] Enhance exceptions
- [ ] Add swagger docs for all routes

## Observations
- Unit tests only simple example in product service
- Integration tests only in product flow as an example

## Migration
```
./gradlew flywayMigrate \
-Dflyway.url='jdbc:postgresql://localhost:5432/<DB_NAME>' \
-Dflyway.user='<DB_USER>' \
-Dflyway.password='<DB_PASS>' \
-Dflyway.baselineOnMigrate=true
```

## Run
- Setup/run postgres database
- Setup application.yaml with database configs
- Build application with embedded gradle `./gradlew clean build`
- Build/run using Dockerfile