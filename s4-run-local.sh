#!/bin/bash

export AWS_URI_MONGO=mongodb://seu_usuario:sua_senha@localhost:27017/pagamento?authSource=admin

mvn spring-boot:run -Dspring-boot.run.profiles=local