#!/bin/bash

# Inicia o script
echo "Script iniciado."

TASK_DEF_NAME="task-def-pagamento"
CLUSTER_NAME="cluster-lanchonete"

# Pega o id da task atual
ID_REV_ATUAL=$(aws ecs list-tasks --cluster ${CLUSTER_NAME} --family ${TASK_DEF_NAME} \
  --output json | jq -r '.taskArns[0]' | awk -F'/' '{print $NF}')

# Clona a task definition mais recente, removendo os campos desnecessários
NEW_TASK_DEFINITION=$(aws ecs describe-task-definition \
  --task-definition ${TASK_DEF_NAME} --output json 2>/dev/null | \
  jq '.taskDefinition' | \
  jq 'del(.taskDefinitionArn, .revision, .status, .requiresAttributes, \
  .compatibilities, .registeredAt, .registeredBy)')

# Registra a nova task definition
REGISTERED_TASK=$(aws ecs register-task-definition --cli-input-json \
  "${NEW_TASK_DEFINITION}" --output json 2>/dev/null)

# Pega o número da nova revisão da task definition
NR_REV_NOVA=$(aws ecs describe-task-definition --task-definition ${TASK_DEF_NAME} \
  --output json | jq '.taskDefinition.revision')

# Inicia a task com a revisão nova
aws ecs run-task --cluster ${CLUSTER_NAME} --task-definition ${TASK_DEF_NAME}:${NR_REV_NOVA} \
  --network-configuration "awsvpcConfiguration={subnets=[subnet-012e4f442963083fd, \
  subnet-019dd408a827986ef],securityGroups=[pagamento-sg]}"

# Para a task anterior
aws ecs stop-task --cluster ${CLUSTER_NAME} --task ${ID_REV_ATUAL}

# Encerra o script
echo "Deploy realizado com sucesso!"
echo "Script finalizado."
