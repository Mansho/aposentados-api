# Estágio 1: Execução leve com o JDK mínimo
FROM eclipse-temurin:17-jre-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o seu .jar gerado na máquina para dentro do container
COPY target/*.jar app.jar

# Expõe a porta padrão do Spring Boot (altere se sua API rodar em outra porta)
EXPOSE 8080

# Comando para rodar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]