# Etapa 1: Compilación de la aplicación
FROM gradle:8-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Etapa 2: Imagen final de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/is2.grupo3-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

# Usamos la ejecución de Shell para asegurar la correcta sustitución de la variable $PORT
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
