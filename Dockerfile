FROM eclipse-temurin:21

WORKDIR /usr/src/geometrica-backend

COPY ./target/GeometricaBackend-0.0.1.jar .

EXPOSE 8080

CMD ["java", "-jar", "GeometricaBackend-0.0.1.jar"]
