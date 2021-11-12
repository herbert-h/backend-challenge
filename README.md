# Backend test

Implementation of the test described [here](https://github.com/hashlab/hiring/blob/master/challenges/pt-br/back-challenge.md)

## Restrictions 
- [x] Services must be written using different languages 
- [x] Services must communicate via gRPC 
- [x] Use docker
- [x] Services can use a shared database

## Implementation
- [x] [Rule Engine](sample-rule-engine)
- [x] [BFF (Backend For Frontend)](sample-bff)

## Docs
- [x] [Postman](docs)
- [x] [Swagger](http://localhost:8080/swagger) *\*Only when BFF is running*

## Run
- Build BFF with embedded gradle
```
cd sample-bff
./gradlew clean build
```
- Then just start docker compose
```
docker compose up -d
```
