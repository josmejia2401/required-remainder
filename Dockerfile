# Etapa 1: Construcción
# FROM maven:3.9.6-eclipse-temurin-17 as build
# WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests

# Etapa 2: Imagen final optimizada
FROM eclipse-temurin:17-jre-alpine

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR construido desde el contexto de build local
COPY target/required-remainder.jar app.jar

# Puerto expuesto (ajusta si tu app corre en otro puerto)
EXPOSE 8080

# Comando para ejecutar la app Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
