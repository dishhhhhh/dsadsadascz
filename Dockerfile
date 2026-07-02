FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY build/libs/is2.grupo3-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]