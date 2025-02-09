#!/bin/bash

# Inicia o script
echo "Script iniciado."

CLUSTER_NAME="cluster-lanchonete"
SERVICE_NAME="pagamento-service"
TASK_DEF_NAME="task-def-pagamento"

# Clona a task definition mais recente, removendo os campos desnecessários
NEW_TASK_DEFINITION=$(aws ecs describe-task-definition \
  --task-definition ${TASK_DEF_NAME} --output json | \
  jq '.taskDefinition | del(.taskDefinitionArn, .revision, .status, .requiresAttributes, .compatibilities, .registeredAt, .registeredBy)')

# Registra a nova task definition
REGISTERED_TASK=$(aws ecs register-task-definition --cli-input-json \
  "${NEW_TASK_DEFINITION}" --output json)

# Pega o número da nova revisão da task definition
NR_REV_NOVA=$(aws ecs describe-task-definition --task-definition ${TASK_DEF_NAME} \
  --output json | jq -r '.taskDefinition.revision')

# Inicia a task com a revisão nova
UPDATE=$(aws ecs update-service --cluster ${CLUSTER_NAME} --service ${SERVICE_NAME} \
  --task-definition ${TASK_DEF_NAME}:${NR_REV_NOVA})
echo $UPDATE

# Encerra o script
echo "Deploy realizado com sucesso!"
echo "Script finalizado."
