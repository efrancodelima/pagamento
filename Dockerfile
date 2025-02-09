## Define a imagem base
FROM openjdk:17-jdk-alpine

## Define as variáveis de ambiente
ENV AWS_URI_MONGO=${AWS_URI_MONGO}

## Define o diretório de trabalho da imagem
WORKDIR /app

## Copia a aplicação para o diretório de trabalho da imagem
COPY target/app-pagamento-*.jar .

## Script para inicializar a aplicação
COPY s1-run-project.sh .
RUN chmod +x s1-run-project.sh
CMD ["sh", "-c", "./s1-run-project.sh"]
