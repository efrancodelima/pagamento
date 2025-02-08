#!/bin/bash

docker run --name mongodb -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=seu_usuario -e MONGO_INITDB_ROOT_PASSWORD=sua_senha mongo

export URL_MONGO_TEST=mongodb://seu_usuario:sua_senha@localhost:27017/pagamento?authSource=admin

mvn spring-boot:run -Dspring-boot.run.profiles=test