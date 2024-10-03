#Construcao da API
FROM maven:3.8.5-openjdk-17-slim AS build

# Definacao o diretório de trabalho no contêiner
WORKDIR /app

# Copiar pom.xml e baixar dependencias (visando cache)
COPY pom.xml . 

# Baixar dependencias
RUN mvn dependency:go-offline

#Copiar codigo fonte
COPY src ./src

#Construir a aplicação
RUN mvn clean package

# JDK necessario para executar a API
FROM openjdk:17-jdk-alpine

# Definacao o diretório de trabalho no contêiner

WORKDIR /app

# Copiar o jar construido
COPY --from=build /app/target/ez-fast-food.jar /app/ez-fast-food.jar


# Exponha a porta em que a aplicação vai rodar
#EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "ez-fast-food.jar"]