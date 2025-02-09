#!/bin/bash

# Inicia o script
echo "Script iniciado."

TASK_DEF_NAME="task-def-pagamento"
CLUSTER_NAME="cluster-lanchonete"

# Pega o número da nova revisão da task definition
NR_REV_NOVA=$(aws ecs describe-task-definition --task-definition ${TASK_DEF_NAME} \
  --output json | jq '.taskDefinition.revision')

# Faz o deploy da revisão nova e remova a anterior
aws ecs run-task --cluster ${CLUSTER_NAME} --task-definition ${TASK_DEF_NAME}:${NR_REV_NOVA}




# Encerra o script
echo "Deploy realizado com sucesso!"
echo "Script finalizado."
