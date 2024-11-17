# Escolhe a imagem base do OpenJDK 11
FROM openjdk:11-jre-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR do projeto para o diretório de trabalho
COPY target/ProductManagerAPI-0.0.1-SNAPSHOT.jar /app/ProductManagerAPI.jar

# Expõe a porta 8080 para acesso externo
EXPOSE 8080

# Define o comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "ProductManagerAPI.jar"]