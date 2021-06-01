# Sample BFF

./gradlew flywayMigrate \
-Dflyway.url='jdbc:postgresql://localhost:5432/hashlab-challenge' \
-Dflyway.user='postgres' \
-Dflyway.password='backend-challenge' \
-Dflyway.baselineOnMigrate=true


docker build -t bff-image -f . .

docker run -p 8080:8080 --rm --name samplebff-container bff-image
